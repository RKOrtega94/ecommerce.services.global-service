package ec.com.ecommerce.global_service.modules.time_periods.application.ports.out;

import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import ec.com.ecommerce.global_service.modules.time_periods.domain.projections.TimePeriodGrpcProjection;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing time periods.
 */
public interface TimePeriodRepository {
    /**
     * Find all time periods based on the provided parameters.
     *
     * @param params a map of parameters for filtering and pagination
     * @return a paginated list of TimePeriodEntity objects
     */
    Page<TimePeriodEntity> findAll(Map<String, Object> params);

    Optional<TimePeriodGrpcProjection> findByIdGrpcProjection(UUID id);
}
