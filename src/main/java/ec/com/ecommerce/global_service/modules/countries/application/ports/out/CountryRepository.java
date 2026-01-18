package ec.com.ecommerce.global_service.modules.countries.application.ports.out;

import ec.com.ecommerce.global_service.modules.countries.domain.entity.CountryEntity;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Country repository
 */
public interface CountryRepository {
    void save(CountryEntity entity);

    Optional<CountryEntity> findByCode(String code);

    Optional<CountryEntity> findById(UUID id);

    Optional<CountryEntity> findByName(String name);

    void delete(CountryEntity entity);

    Page<CountryEntity> findAll(Map<String, Object> params);
}
