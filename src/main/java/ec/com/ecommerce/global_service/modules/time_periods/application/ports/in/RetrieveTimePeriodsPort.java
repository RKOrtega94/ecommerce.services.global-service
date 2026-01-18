package ec.com.ecommerce.global_service.modules.time_periods.application.ports.in;

import ec.com.ecommerce.global_service.modules.time_periods.application.ports.out.TimePeriodRepository;
import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import ec.com.ecommerce.global_service.modules.time_periods.domain.usecases.RetrieveTimePeriodsUseCase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RetrieveTimePeriodsPort implements RetrieveTimePeriodsUseCase {
    private final TimePeriodRepository repository;

    public RetrieveTimePeriodsPort(TimePeriodRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<TimePeriodEntity> execute(Map<String, Object> params) {
        return repository.findAll(params);
    }
}
