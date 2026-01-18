package ec.com.ecommerce.global_service.modules.time_periods.domain.projections;

import ec.com.ecommerce.global_service.modules.time_periods.domain.enums.PeriodType;

import java.util.UUID;

/**
 * gRPC Projection interface for Time Periods.
 */
public interface TimePeriodGrpcProjection {
    UUID getId();

    String getLabel();

    String getDescription();

    PeriodType getPeriodType();

    Integer getValue();
}
