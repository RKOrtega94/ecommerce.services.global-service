package ec.com.ecommerce.modules.states.application.services;

import ec.com.ecommerce.modules.states.application.dtos.request.CreateStateRequest;
import ec.com.ecommerce.modules.states.application.dtos.request.UpdateStateRequest;
import ec.com.ecommerce.modules.states.application.dtos.response.StateResponse;
import ec.com.ecommerce.modules.states.domain.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final RetrieveStatesUseCase retrieveStates;
    private final RetrieveStateUseCase retrieveState;
    private final UpdateStateUseCase updateState;
    private final CreateStateUseCase createState;
    private final DeleteStateUseCase deleteState;

    @Override
    public void create(CreateStateRequest request) {
        createState.execute(request);
    }

    @Override
    public void update(UUID id, UpdateStateRequest request) {
        updateState.execute(id, request);
    }

    @Override
    public StateResponse retrieve(UUID id) {
        return retrieveState.execute(id);
    }

    @Override
    public Page<StateResponse> retrieve(Map<String, Object> params) {
        return retrieveStates.execute(params);
    }

    @Override
    public void delete(UUID id) {
        deleteState.execute(id);
    }
}
