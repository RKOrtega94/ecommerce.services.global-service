package ec.com.ecommerce.global_service.modules.taxes.application.ports.out;

import ec.com.ecommerce.global_service.modules.taxes.domain.entity.TaxEntity;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Tax repository
 */
public interface TaxRepository {
    void save(TaxEntity entity);

    Optional<TaxEntity> findById(UUID id);

    Optional<TaxEntity> findByName(String name);

    void delete(TaxEntity entity);

    Page<TaxEntity> findAll(Map<String, Object> params);
}

