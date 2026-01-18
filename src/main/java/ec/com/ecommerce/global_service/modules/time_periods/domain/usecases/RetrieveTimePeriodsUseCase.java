package ec.com.ecommerce.global_service.modules.time_periods.domain.usecases;

import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Use case interface for retrieving time periods based on provided parameters.
 */
public interface RetrieveTimePeriodsUseCase {
    /**
     * Executes the use case to retrieve time periods.
     *
     * @param params a map of parameters for filtering and pagination
     * @return a paginated list of TimePeriodEntity objects
     */
    Page<TimePeriodEntity> execute(Map<String, Object> params);
}
