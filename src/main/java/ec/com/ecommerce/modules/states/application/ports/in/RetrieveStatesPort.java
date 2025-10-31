package ec.com.ecommerce.modules.states.application.ports.in;

import ec.com.ecommerce.modules.states.application.dtos.response.StateResponse;
import ec.com.ecommerce.modules.states.application.mapper.StateMapper;
import ec.com.ecommerce.modules.states.application.ports.out.StateRepository;
import ec.com.ecommerce.modules.states.domain.usecases.RetrieveStatesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RetrieveStatesPort implements RetrieveStatesUseCase {
    private final StateMapper mapper;
    private final StateRepository repository;

    @Override
    public Page<StateResponse> execute(Map<String, Object> params) {
        return repository.findAll(params).map(mapper::toResponse);
    }
}
