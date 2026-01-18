package ec.com.ecommerce.global_service.modules.time_periods.application.service;

import ec.com.ecommerce.grpc_lib.global.TimePeriodGrpcResponse;
import ec.com.ecommerce.global_service.modules.time_periods.application.dtos.response.TimePeriodResponse;
import ec.com.ecommerce.global_service.modules.time_periods.application.mapper.TimePeriodMapper;
import ec.com.ecommerce.global_service.modules.time_periods.domain.usecases.RetrieveTimePeriodByIdForGrpcUseCase;
import ec.com.ecommerce.global_service.modules.time_periods.domain.usecases.RetrieveTimePeriodsUseCase;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TimePeriodServiceImpl implements TimePeriodService {
    private final RetrieveTimePeriodsUseCase retrieveTimePeriods;
    private final RetrieveTimePeriodByIdForGrpcUseCase retrieveTimePeriodByIdForGrpc;

    private final TimePeriodMapper mapper;

    public TimePeriodServiceImpl(RetrieveTimePeriodsUseCase retrieveTimePeriods, RetrieveTimePeriodByIdForGrpcUseCase retrieveTimePeriodByIdForGrpc, TimePeriodMapper mapper) {
        this.retrieveTimePeriods = retrieveTimePeriods;
        this.retrieveTimePeriodByIdForGrpc = retrieveTimePeriodByIdForGrpc;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = "timePeriodsCache", key = "'allTimePeriods:' + #params", unless = "#result == null || #result.isEmpty()")
    public Page<TimePeriodResponse> retrieve(Map<String, Object> params) {
        var timePeriodsPage = retrieveTimePeriods.execute(params);
        return timePeriodsPage.map(mapper::toResponse);
    }

    @Override
    @Cacheable(value = "timePeriodsCache", key = "'timePeriodById:' + #id", unless = "#result == null")
    public TimePeriodGrpcResponse retrieveGrpcById(String id) {
        var timePeriodOpt = retrieveTimePeriodByIdForGrpc.execute(UUID.fromString(id));
        return timePeriodOpt.map(mapper::toGrpcResponse).orElse(null);
    }
}