package ec.com.ecommerce.modules.states.domain.usecases;

import ec.com.ecommerce.modules.states.application.dtos.request.UpdateStateRequest;

import java.util.UUID;

/**
 * Update state use case
 */
public interface UpdateStateUseCase {
    /**
     * Update a state
     *
     * @param request the update state request
     */
    void execute(UUID id, UpdateStateRequest request);
}
