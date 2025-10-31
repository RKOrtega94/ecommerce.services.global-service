package ec.com.ecommerce.modules.states.application.services;

import ec.com.ecommerce.modules.states.application.dtos.request.CreateStateRequest;
import ec.com.ecommerce.modules.states.application.dtos.request.UpdateStateRequest;
import ec.com.ecommerce.modules.states.application.dtos.response.StateResponse;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.UUID;

public interface StateService {
    void create(CreateStateRequest request);

    void update(UUID id, UpdateStateRequest request);

    StateResponse retrieve(UUID id);

    Page<StateResponse> retrieve(Map<String, Object> params);

    void delete(UUID id);
}
