package ec.com.ecommerce.modules.states.domain.specification;

import ec.com.ecommerce.modules.states.domain.entity.StateEntity;
import ec.com.ecommerce.specification.SpecificationBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * State specifications
 */
@Slf4j
public class StateSpecification {
    private StateSpecification() {
        throw new IllegalStateException("Utility class");
    }

    private static final String NAME = "name";
    private static final String COUNTRY_ID = "country.id";
    private static final String STATUS = "status";

    public static Specification<StateEntity> stateSpecification(Map<String, Object> params) {
        SpecificationBuilder<StateEntity> spec = SpecificationBuilder.builder();
        params.forEach((key, value) -> {
            switch (key) {
                case NAME -> spec.like(NAME, value.toString());
                case COUNTRY_ID -> spec.equals(COUNTRY_ID, value.toString());
                case STATUS -> spec.equals(STATUS, value.toString());
                default -> log.warn("Unknown filter key: {}", key);
            }
        });
        if (!params.containsKey(STATUS)) spec.notEquals(STATUS, "DELETED");
        return spec.build();
    }
}
