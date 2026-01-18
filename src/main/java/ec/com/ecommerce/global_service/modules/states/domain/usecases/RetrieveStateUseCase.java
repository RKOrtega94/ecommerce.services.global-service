package ec.com.ecommerce.global_service.modules.states.domain.usecases;

import ec.com.ecommerce.global_service.modules.states.application.dtos.response.StateResponse;

import java.util.UUID;

/**
 * Retrieve state use case
 */
public interface RetrieveStateUseCase {
    /**
     * Retrieve a state by id
     *
     * @param id the unique id of state
     * @return the state response
     */
    StateResponse execute(UUID id);
}
