package ec.com.ecommerce.modules.taxes.adapter.persistence;

import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.modules.taxes.application.ports.out.TaxRepository;
import ec.com.ecommerce.modules.taxes.domain.entity.TaxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static ec.com.ecommerce.modules.taxes.domain.specification.TaxSpecificationBuilder.taxSpecification;
import static ec.com.ecommerce.pagination.PaginationUtil.pageable;

@Repository
@RequiredArgsConstructor
public class TaxRepositoryAdapter implements TaxRepository {
    private final TaxJpaRepository jpa;

    @Override
    public void save(TaxEntity entity) {
        jpa.save(entity);
    }

    @Override
    public Optional<TaxEntity> findById(UUID id) {
        return jpa.findById(id).filter(entity -> !entity.getStatus().equals(Status.DELETED));
    }

    @Override
    public Optional<TaxEntity> findByName(String name) {
        return jpa.findByName(name);
    }

    @Override
    public void delete(TaxEntity entity) {
        entity.setStatus(Status.DELETED);
        entity.setDeletedAt(OffsetDateTime.now());
        jpa.save(entity);
    }

    @Override
    public Page<TaxEntity> findAll(Map<String, Object> params) {
        return jpa.findAll(taxSpecification(params), pageable(params));
    }
}