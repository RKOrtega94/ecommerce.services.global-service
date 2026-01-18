package ec.com.ecommerce.global_service.modules.cities.adapter.persistence;

import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.global_service.modules.cities.application.ports.out.CityRepository;
import ec.com.ecommerce.global_service.modules.cities.domain.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static ec.com.ecommerce.global_service.modules.cities.domain.specification.CitySpecification.citySpecification;
import static ec.com.ecommerce.pagination.PaginationUtil.pageable;

@Repository
public class CityRepositoryAdapter implements CityRepository {
    private final CityJpaRepository jpa;

    public CityRepositoryAdapter(CityJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(CityEntity entity) {
        jpa.save(entity);
    }

    @Override
    public Optional<CityEntity> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Page<CityEntity> findAll(Map<String, Object> params) {
        return jpa.findAll(citySpecification(params), pageable(params));
    }

    @Override
    public void delete(CityEntity entity) {
        entity.setStatus(Status.DELETED);
        entity.setDeletedAt(OffsetDateTime.now());
        jpa.save(entity);
    }
}

