-- ============================================================================
-- SISTEMA DE AUDITORÍA AUTOMÁTICA PARA POSTGRESQL 16+
-- Versión Simplificada y Funcional
-- ============================================================================
-- ----------------------------------------------------------------------------
-- PASO 1: FUNCIONES TRIGGER PARA TIMESTAMPS
-- ----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_set_timestamps()
    RETURNS TRIGGER
    LANGUAGE plpgsql AS
$$
BEGIN
    IF TG_OP = 'INSERT' THEN
        NEW.created_at := CURRENT_TIMESTAMP;
        NEW.updated_at := CURRENT_TIMESTAMP;
    ELSIF TG_OP = 'UPDATE' THEN
        NEW.created_at := OLD.created_at;
        NEW.updated_at := CURRENT_TIMESTAMP;
    END IF;
    RETURN NEW;
END;
$$;

CREATE OR REPLACE FUNCTION trigger_set_deleted_at()
    RETURNS TRIGGER
    LANGUAGE plpgsql AS
$$
BEGIN
    IF NEW.status = 'DELETED' AND (OLD.status IS NULL OR OLD.status != 'DELETED') THEN
        NEW.deleted_at := CURRENT_TIMESTAMP;
    ELSIF NEW.status != 'DELETED' AND OLD.status = 'DELETED' THEN
        NEW.deleted_at := NULL;
    END IF;
    RETURN NEW;
END;
$$;

-- ----------------------------------------------------------------------------
-- PASO 2: FUNCIÓN PARA AGREGAR COLUMNAS Y TRIGGERS
-- ----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION setup_audit_for_table(
    p_schema_name TEXT,
    p_table_name TEXT
)
    RETURNS VOID
    LANGUAGE plpgsql AS
$$
DECLARE
    v_full_table TEXT;
    v_sql        TEXT;
BEGIN
    v_full_table := quote_ident(p_schema_name) || '.' || quote_ident(p_table_name);

    RAISE NOTICE '===> Procesando tabla: %', v_full_table;

    -- Agregar STATUS si no existe
    IF NOT EXISTS (SELECT 1
                   FROM information_schema.columns
                   WHERE table_schema = p_schema_name
                     AND table_name = p_table_name
                     AND column_name = 'status') THEN
        v_sql := format(
                'ALTER TABLE %s ADD COLUMN status VARCHAR(10) DEFAULT ''ACTIVE''
                 CHECK (status IN (''ACTIVE'', ''INACTIVE'', ''DELETED''))',
                v_full_table
                 );
        EXECUTE v_sql;
        RAISE NOTICE '  ✓ Columna status agregada';
    END IF;

    -- Agregar CREATED_AT si no existe
    IF NOT EXISTS (SELECT 1
                   FROM information_schema.columns
                   WHERE table_schema = p_schema_name
                     AND table_name = p_table_name
                     AND column_name = 'created_at') THEN
        v_sql := format(
                'ALTER TABLE %s ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP',
                v_full_table
                 );
        EXECUTE v_sql;
        RAISE NOTICE '  ✓ Columna created_at agregada';
    END IF;

    -- Agregar UPDATED_AT si no existe
    IF NOT EXISTS (SELECT 1
                   FROM information_schema.columns
                   WHERE table_schema = p_schema_name
                     AND table_name = p_table_name
                     AND column_name = 'updated_at') THEN
        v_sql := format(
                'ALTER TABLE %s ADD COLUMN updated_at TIMESTAMP DEFAULT NOW()',
                v_full_table
                 );
        EXECUTE v_sql;
        RAISE NOTICE '  ✓ Columna updated_at agregada';
    END IF;

    -- Agregar DELETED_AT si no existe
    IF NOT EXISTS (SELECT 1
                   FROM information_schema.columns
                   WHERE table_schema = p_schema_name
                     AND table_name = p_table_name
                     AND column_name = 'deleted_at') THEN
        v_sql := format(
                'ALTER TABLE %s ADD COLUMN deleted_at TIMESTAMP',
                v_full_table
                 );
        EXECUTE v_sql;
        RAISE NOTICE '  ✓ Columna deleted_at agregada';
    END IF;

    -- Agregar CREATED_BY si no existe
    IF NOT EXISTS (SELECT 1
                   FROM information_schema.columns
                   WHERE table_schema = p_schema_name
                     AND table_name = p_table_name
                     AND column_name = 'created_by') THEN
        v_sql := format(
                'ALTER TABLE %s ADD COLUMN created_by VARCHAR(100)',
                v_full_table
                 );
        EXECUTE v_sql;
        RAISE NOTICE '  ✓ Columna created_by agregada';
    END IF;

    -- Agregar UPDATED_BY si no existe
    IF NOT EXISTS (SELECT 1
                   FROM information_schema.columns
                   WHERE table_schema = p_schema_name
                     AND table_name = p_table_name
                     AND column_name = 'updated_by') THEN
        v_sql := format(
                'ALTER TABLE %s ADD COLUMN updated_by VARCHAR(100)',
                v_full_table
                 );
        EXECUTE v_sql;
        RAISE NOTICE '  ✓ Columna updated_by agregada';
    END IF;

    -- Crear trigger para timestamps
    v_sql := format('DROP TRIGGER IF EXISTS trg_%s_timestamps ON %s', p_table_name, v_full_table);
    EXECUTE v_sql;

    v_sql := format(
            'CREATE TRIGGER trg_%s_timestamps
             BEFORE INSERT OR UPDATE ON %s
             FOR EACH ROW
             EXECUTE FUNCTION trigger_set_timestamps()',
            p_table_name, v_full_table
             );
    EXECUTE v_sql;
    RAISE NOTICE '  ✓ Trigger timestamps creado';

    -- Crear trigger para deleted_at
    v_sql := format('DROP TRIGGER IF EXISTS trg_%s_deleted_at ON %s', p_table_name, v_full_table);
    EXECUTE v_sql;

    v_sql := format(
            'CREATE TRIGGER trg_%s_deleted_at
             BEFORE UPDATE ON %s
             FOR EACH ROW
             EXECUTE FUNCTION trigger_set_deleted_at()',
            p_table_name, v_full_table
             );
    EXECUTE v_sql;
    RAISE NOTICE '  ✓ Trigger deleted_at creado';

    RAISE NOTICE '===> Auditoría completada para: %', v_full_table;
END;
$$;

-- ----------------------------------------------------------------------------
-- PASO 3: EVENT TRIGGER PRINCIPAL
-- ----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION event_trigger_auto_audit()
    RETURNS event_trigger
    LANGUAGE plpgsql AS
$$
DECLARE
    v_obj    RECORD;
    v_schema TEXT;
    v_table  TEXT;
BEGIN
    RAISE NOTICE '>>> Event Trigger ejecutándose...';

    FOR v_obj IN SELECT * FROM pg_event_trigger_ddl_commands()
        LOOP
            RAISE NOTICE '>>> Objeto detectado: type=%, identity=%',
                v_obj.object_type, v_obj.object_identity;

            IF v_obj.object_type = 'table' THEN
                v_schema := v_obj.schema_name;

                -- Extraer nombre de tabla del object_identity
                -- Formato puede ser: "schema"."table" o solo "table"
                IF v_obj.object_identity LIKE '%.%' THEN
                    v_table := regexp_replace(v_obj.object_identity, '^.*\.', '');
                ELSE
                    v_table := v_obj.object_identity;
                END IF;

                -- Limpiar comillas
                v_schema := replace(v_schema, '"', '');
                v_table := replace(v_table, '"', '');

                RAISE NOTICE '>>> Extraído: schema=%, table=%', v_schema, v_table;

                -- Excluir schemas del sistema
                IF v_schema NOT IN ('pg_catalog', 'information_schema', 'pg_toast')
                    AND v_table NOT LIKE 'pg_%'
                    AND v_table NOT LIKE 'sql_%' THEN

                    BEGIN
                        RAISE NOTICE '>>> Aplicando auditoría a %.%', v_schema, v_table;
                        PERFORM setup_audit_for_table(v_schema, v_table);
                    EXCEPTION
                        WHEN OTHERS THEN
                            RAISE WARNING '>>> ERROR en %.%: %', v_schema, v_table, SQLERRM;
                    END;
                ELSE
                    RAISE NOTICE '>>> Tabla excluida: %.%', v_schema, v_table;
                END IF;
            END IF;
        END LOOP;

    RAISE NOTICE '>>> Event Trigger finalizado';
END;
$$;

-- Eliminar event trigger anterior
DROP EVENT TRIGGER IF EXISTS auto_audit_on_create_table;

-- Crear event trigger
CREATE EVENT TRIGGER auto_audit_on_create_table
    ON ddl_command_end
    WHEN TAG IN ('CREATE TABLE')
EXECUTE FUNCTION event_trigger_auto_audit();

-- Habilitar
    ALTER EVENT TRIGGER auto_audit_on_create_table ENABLE;

-- ----------------------------------------------------------------------------
-- FUNCIONES DE UTILIDAD
-- ----------------------------------------------------------------------------

-- Verificar estado
CREATE OR REPLACE FUNCTION check_auto_audit_status()
    RETURNS TABLE
            (
                trigger_name TEXT,
                is_enabled   TEXT,
                event        TEXT,
                tags         TEXT[]
            )
    LANGUAGE SQL
AS
$$
SELECT evtname::TEXT,
       CASE evtenabled
           WHEN 'O' THEN 'HABILITADO ✓'
           WHEN 'D' THEN 'DESHABILITADO ✗'
           ELSE 'DESCONOCIDO: ' || evtenabled::TEXT
           END,
       evtevent::TEXT,
       evttags
FROM pg_event_trigger
WHERE evtname = 'auto_audit_on_create_table';
$$;

-- Habilitar/Deshabilitar
CREATE OR REPLACE FUNCTION enable_auto_audit()
    RETURNS TEXT
    LANGUAGE plpgsql AS
$$
BEGIN
    ALTER EVENT TRIGGER auto_audit_on_create_table ENABLE;
    RETURN '✓ Auditoría automática HABILITADA';
END;
$$;

CREATE OR REPLACE FUNCTION disable_auto_audit()
    RETURNS TEXT
    LANGUAGE plpgsql AS
$$
BEGIN
    ALTER EVENT TRIGGER auto_audit_on_create_table DISABLE;
    RETURN '✗ Auditoría automática DESHABILITADA';
END;
$$;

-- Aplicar a tabla específica manualmente
CREATE OR REPLACE FUNCTION apply_audit_to_table(
    p_table_name TEXT,
    p_schema_name TEXT DEFAULT '@@schema_name@@'
)
    RETURNS TEXT
    LANGUAGE plpgsql AS
$$
BEGIN
    PERFORM setup_audit_for_table(p_schema_name, p_table_name);
    RETURN format('✓ Auditoría aplicada a %.%', p_schema_name, p_table_name);
EXCEPTION
    WHEN OTHERS THEN
        RETURN format('✗ Error: %', SQLERRM);
END;
$$;

-- Aplicar a todas las tablas existentes
CREATE OR REPLACE FUNCTION apply_audit_to_all_tables(
    p_schema_name TEXT DEFAULT '@@schema_name@@'
)
    RETURNS TABLE
            (
                table_name TEXT,
                status     TEXT
            )
    LANGUAGE plpgsql
AS
$$
DECLARE
    v_table RECORD;
BEGIN
    FOR v_table IN
        SELECT t.table_name
        FROM information_schema.tables t
        WHERE t.table_schema = p_schema_name
          AND t.table_type = 'BASE TABLE'
          AND t.table_name NOT LIKE 'pg_%'
          AND t.table_name NOT LIKE 'sql_%'
        ORDER BY t.table_name
        LOOP
            BEGIN
                PERFORM setup_audit_for_table(p_schema_name, v_table.table_name);
                table_name := v_table.table_name;
                status := '✓ OK';
                RETURN NEXT;
            EXCEPTION
                WHEN OTHERS THEN
                    table_name := v_table.table_name;
                    status := '✗ ERROR: ' || SQLERRM;
                    RETURN NEXT;
            END;
        END LOOP;
END;
$$;

-- ============================================================================
-- VERIFICACIÓN Y PRUEBAS
-- ============================================================================

-- 1. Verificar que el event trigger está instalado
SELECT *
FROM check_auto_audit_status();

-- 2. Ver todos los event triggers del sistema
SELECT evtname,
       evtevent,
       evtenabled,
       evttags,
       evtowner::regrole
FROM pg_event_trigger
ORDER BY evtname;
