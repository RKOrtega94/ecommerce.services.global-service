package ec.com.ecommerce.global_service.modules.states.application.ports.in;

import ec.com.ecommerce.global_service.modules.states.application.dtos.response.StateResponse;
import ec.com.ecommerce.global_service.modules.states.application.mapper.StateMapper;
import ec.com.ecommerce.global_service.modules.states.application.ports.out.StateRepository;
import ec.com.ecommerce.global_service.modules.states.domain.usecases.RetrieveStateUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RetrieveStatePort implements RetrieveStateUseCase {
    private final StateMapper mapper;
    private final StateRepository repository;

    @Override
    public StateResponse execute(UUID id) {
        var entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("State not found"));
        return mapper.toResponse(entity);
    }
}
