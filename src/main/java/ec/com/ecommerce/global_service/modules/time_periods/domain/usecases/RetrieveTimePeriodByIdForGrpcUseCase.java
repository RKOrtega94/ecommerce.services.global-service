package ec.com.ecommerce.global_service.modules.time_periods.domain.usecases;

import ec.com.ecommerce.global_service.modules.time_periods.domain.projections.TimePeriodGrpcProjection;

import java.util.Optional;
import java.util.UUID;

public interface RetrieveTimePeriodByIdForGrpcUseCase {
    Optional<TimePeriodGrpcProjection> execute(UUID id);
}
