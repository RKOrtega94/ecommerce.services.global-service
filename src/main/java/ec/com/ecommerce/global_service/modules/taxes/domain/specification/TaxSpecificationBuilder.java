package ec.com.ecommerce.global_service.modules.taxes.domain.specification;

import ec.com.ecommerce.global_service.modules.taxes.domain.entity.TaxEntity;
import ec.com.ecommerce.specification.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * Utility class for building tax specifications.
 */
public class TaxSpecificationBuilder {
    private TaxSpecificationBuilder() {
        throw new IllegalStateException("Utility class");
    }

    private static final String NAME = "name";
    private static final String PERCENTAGE = "percentage";
    private static final String STATUS = "status";
    private static final String COUNTRY_ID = "countryId";

    public static Specification<TaxEntity> taxSpecification(Map<String, Object> params) {
        SpecificationBuilder<TaxEntity> spec = SpecificationBuilder.builder();
        if (params.containsKey(NAME)) spec.like(NAME, params.get(NAME).toString());
        if (params.containsKey(PERCENTAGE)) spec.equals(PERCENTAGE, params.get(PERCENTAGE).toString());
        if (params.containsKey(COUNTRY_ID)) spec.equals("country.id", params.get(COUNTRY_ID).toString());
        if (params.containsKey(STATUS)) spec.equals(STATUS, params.get(STATUS).toString());
        else spec.notEquals(STATUS, "DELETED");
        return spec.build();
    }
}