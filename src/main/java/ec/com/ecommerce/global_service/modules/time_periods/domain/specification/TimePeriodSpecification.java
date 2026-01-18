package ec.com.ecommerce.global_service.modules.time_periods.domain.specification;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import ec.com.ecommerce.specification.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

import static ec.com.ecommerce.enums.Status.DELETED;

/**
 * Specification class for TimePeriod-related criteria.
 */
public class TimePeriodSpecification {
    private TimePeriodSpecification() {
    }

    private static final String LABEL = "label";
    private static final String DESCRIPTION = "description";
    private static final String PERIOD_TYPE = "periodType";
    private static final String STATUS = "status";

    /**
     * Builds a Specification for TimePeriodEntity.
     *
     * @return a Specification for TimePeriodEntity
     */
    public static Specification<TimePeriodEntity> buildTimePeriodSpecification(Map<String, Object> params) {
        var specification = SpecificationBuilder.<TimePeriodEntity>builder();
        if (params.containsKey(LABEL)) specification.like(LABEL, params.get(LABEL).toString());
        if (params.containsKey(DESCRIPTION)) specification.like(DESCRIPTION, params.get(DESCRIPTION).toString());
        if (params.containsKey(PERIOD_TYPE)) {
            if (params.get(PERIOD_TYPE) instanceof String)
                specification.equals(PERIOD_TYPE, params.get(PERIOD_TYPE).toString());
            else if (params.get(PERIOD_TYPE) instanceof Iterable<?>) {
                final ObjectMapper mapper = new ObjectMapper();
                var values = mapper.convertValue(params.get(PERIOD_TYPE), List.class);
                specification.in(PERIOD_TYPE, values);
            }
        }
        if (params.containsKey(STATUS)) specification.equals(STATUS, params.get(STATUS).toString());
        else specification.notEquals(STATUS, DELETED);
        return specification.build();
    }
}
