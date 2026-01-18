package ec.com.ecommerce.global_service.modules.time_periods.application.service;

import ec.com.ecommerce.grpc_lib.global.TimePeriodGrpcResponse;
import ec.com.ecommerce.global_service.modules.time_periods.application.dtos.response.TimePeriodResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Service interface for managing time periods.
 */
public interface TimePeriodService {
    Page<TimePeriodResponse> retrieve(Map<String, Object> params);

    TimePeriodGrpcResponse retrieveGrpcById(String id);
}
