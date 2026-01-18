package ec.com.ecommerce.global_service.modules.states.application.mapper;

import ec.com.ecommerce.global_service.modules.states.application.dtos.request.CreateStateRequest;
import ec.com.ecommerce.global_service.modules.states.application.dtos.request.UpdateStateRequest;
import ec.com.ecommerce.global_service.modules.states.application.dtos.response.StateResponse;
import ec.com.ecommerce.global_service.modules.states.domain.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * State mapper
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StateMapper {
    /**
     * Map from request to entity
     *
     * @param request create state request
     * @return state entity
     */
    @Mapping(target = "country", ignore = true)
    StateEntity toEntity(CreateStateRequest request);

    /**
     * Map from update request to entity
     *
     * @param entity  the state entity
     * @param request the update state request
     */
    @Mapping(target = "country", ignore = true)
    void update(@MappingTarget StateEntity entity, UpdateStateRequest request);

    /**
     * Map from entity to response
     *
     * @param entity state entity
     * @return state response
     */
    StateResponse toResponse(StateEntity entity);
}
