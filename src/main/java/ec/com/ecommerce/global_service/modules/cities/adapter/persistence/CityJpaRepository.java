package ec.com.ecommerce.global_service.modules.cities.adapter.persistence;

import ec.com.ecommerce.global_service.modules.cities.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityJpaRepository extends JpaRepository<CityEntity, UUID>, PagingAndSortingRepository<CityEntity, UUID>, JpaSpecificationExecutor<CityEntity> {
}

