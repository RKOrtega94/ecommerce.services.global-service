package ec.com.ecommerce.global_service.modules.time_periods.application.ports.in;

import ec.com.ecommerce.global_service.modules.time_periods.application.ports.out.TimePeriodRepository;
import ec.com.ecommerce.global_service.modules.time_periods.domain.projections.TimePeriodGrpcProjection;
import ec.com.ecommerce.global_service.modules.time_periods.domain.usecases.RetrieveTimePeriodByIdForGrpcUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RetrieveTimePeriodByIdForGrpcPort implements RetrieveTimePeriodByIdForGrpcUseCase {
    private final TimePeriodRepository repository;

    public RetrieveTimePeriodByIdForGrpcPort(TimePeriodRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TimePeriodGrpcProjection> execute(UUID id) {
        return repository.findByIdGrpcProjection(id);
    }
}
