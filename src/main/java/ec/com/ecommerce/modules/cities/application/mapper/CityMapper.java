package ec.com.ecommerce.modules.cities.application.mapper;

import ec.com.ecommerce.modules.cities.application.dtos.request.CreateCityRequest;
import ec.com.ecommerce.modules.cities.application.dtos.request.UpdateCityRequest;
import ec.com.ecommerce.modules.cities.application.dtos.response.CityResponse;
import ec.com.ecommerce.modules.cities.domain.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * City mapper
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {
    @Mapping(target = "state", ignore = true)
    CityEntity toEntity(CreateCityRequest request);

    @Mapping(target = "state", ignore = true)
    void update(@MappingTarget CityEntity entity, UpdateCityRequest request);

    CityResponse toResponse(CityEntity entity);
}

