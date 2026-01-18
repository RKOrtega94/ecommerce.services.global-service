package ec.com.ecommerce.global_service.modules.taxes.adapter.persistence;

import ec.com.ecommerce.global_service.modules.taxes.domain.entity.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaxJpaRepository extends JpaRepository<TaxEntity, UUID>, JpaSpecificationExecutor<TaxEntity> {
    Optional<TaxEntity> findByName(String name);
}

