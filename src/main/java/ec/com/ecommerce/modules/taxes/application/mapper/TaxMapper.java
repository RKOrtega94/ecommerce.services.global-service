package ec.com.ecommerce.modules.taxes.application.mapper;

import ec.com.ecommerce.modules.taxes.application.dtos.request.CreateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.dtos.request.UpdateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.dtos.response.TaxResponse;
import ec.com.ecommerce.modules.taxes.domain.entity.TaxEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * TaxMapper
 */
@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TaxMapper {
    /**
     * toEntity
     *
     * @param request CreateTaxRequest
     * @return TaxEntity
     */
    @Mapping(target = "country", ignore = true)
    TaxEntity toEntity(CreateTaxRequest request);

    /**
     * update
     *
     * @param entity  the entity to be updated
     * @param request the request with the new values
     */
    @Mapping(target = "country", ignore = true)
    void update(@MappingTarget TaxEntity entity, UpdateTaxRequest request);

    /**
     * toResponse
     *
     * @param entity TaxEntity
     * @return TaxResponse
     */
    @Mapping(target = "countryId", source = "country.id")
    TaxResponse toResponse(TaxEntity entity);
}