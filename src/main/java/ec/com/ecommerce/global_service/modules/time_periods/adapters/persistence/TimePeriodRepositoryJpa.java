package ec.com.ecommerce.global_service.modules.time_periods.adapters.persistence;

import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import ec.com.ecommerce.global_service.modules.time_periods.domain.projections.TimePeriodGrpcProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TimePeriodRepositoryJpa extends JpaRepository<TimePeriodEntity, UUID>, PagingAndSortingRepository<TimePeriodEntity, UUID>, JpaSpecificationExecutor<TimePeriodEntity> {
    @Query("""
            SELECT
                tpe
            FROM TimePeriodEntity tpe
            WHERE tpe.id = :id
            """)
    Optional<TimePeriodGrpcProjection> findByIdGrpcProjection(UUID id);
}
