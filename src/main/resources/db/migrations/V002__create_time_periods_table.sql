-- Remove numspell extension dependency (was: CREATE EXTENSION IF NOT EXISTS "numspell");
-- Labels will be generated without converting numbers to words to keep portability across PostgreSQL installs.

-- Create time_period_types table
DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'time_period_type') THEN
            CREATE TYPE time_period_type AS ENUM (
                'DAILY',
                'WEEKLY',
                'MONTHLY',
                'QUARTERLY',
                'YEARLY'
                );
            RAISE NOTICE 'Created time_period_type enum type';
        END IF;
    END
$$;


-- Create time periods table
CREATE TABLE IF NOT EXISTS TIME_PERIODS
(
    ID          UUID PRIMARY KEY DEFAULT GEN_RANDOM_UUID(),
    PERIOD_TYPE TIME_PERIOD_TYPE NOT NULL,
    VALUE       INT              NOT NULL,
    LABEL       VARCHAR(100)     NOT NULL,
    DESCRIPTION TEXT
);

-- Add unique constraint for period_type and value
CREATE UNIQUE INDEX UNIQUE_TIME_PERIOD ON TIME_PERIODS (PERIOD_TYPE, VALUE);

-- Create index on label for faster lookups
CREATE INDEX IDX_TIME_PERIOD_LABEL ON TIME_PERIODS (LABEL);