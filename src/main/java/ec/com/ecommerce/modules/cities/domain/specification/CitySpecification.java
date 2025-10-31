package ec.com.ecommerce.modules.cities.domain.specification;

import ec.com.ecommerce.modules.cities.domain.entity.CityEntity;
import ec.com.ecommerce.specification.SpecificationBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * City specifications
 */
@Slf4j
public class CitySpecification {
    private CitySpecification() {
        throw new IllegalStateException("Utility class");
    }

    private static final String NAME = "name";
    private static final String STATE_ID = "state.id";
    private static final String STATUS = "status";

    public static Specification<CityEntity> citySpecification(Map<String, Object> params) {
        SpecificationBuilder<CityEntity> spec = SpecificationBuilder.builder();
        params.forEach((key, value) -> {
            switch (key) {
                case NAME -> spec.like(NAME, value.toString());
                case STATE_ID -> spec.equals(STATE_ID, value.toString());
                case STATUS -> spec.equals(STATUS, value.toString());
                default -> log.warn("Unknown filter key: {}", key);
            }
        });
        if (!params.containsKey(STATUS)) spec.notEquals(STATUS, "DELETED");
        return spec.build();
    }
}

