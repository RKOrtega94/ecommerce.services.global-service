package ec.com.ecommerce.global_service.modules.cities.application.ports.out;

import ec.com.ecommerce.global_service.modules.cities.domain.entity.CityEntity;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CityRepository {
    void save(CityEntity entity);

    Optional<CityEntity> findById(UUID id);

    Page<CityEntity> findAll(Map<String, Object> params);

    void delete(CityEntity entity);
}

