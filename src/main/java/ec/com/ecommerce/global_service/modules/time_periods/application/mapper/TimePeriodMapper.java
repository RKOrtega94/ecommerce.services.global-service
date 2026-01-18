package ec.com.ecommerce.global_service.modules.time_periods.application.mapper;

import ec.com.ecommerce.grpc_lib.global.TimePeriodGrpcResponse;
import ec.com.ecommerce.global_service.modules.time_periods.application.dtos.response.TimePeriodResponse;
import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import ec.com.ecommerce.global_service.modules.time_periods.domain.projections.TimePeriodGrpcProjection;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE, unmappedSourcePolicy = IGNORE)
public interface TimePeriodMapper {
    TimePeriodResponse toResponse(TimePeriodEntity entity);

    TimePeriodGrpcResponse toGrpc(TimePeriodResponse response);

    TimePeriodGrpcResponse toGrpcResponse(TimePeriodGrpcProjection projection);
}
