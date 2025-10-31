package ec.com.ecommerce.modules.states.domain.usecases;

import ec.com.ecommerce.modules.states.application.dtos.request.CreateStateRequest;

/**
 * Create state use case
 */
public interface CreateStateUseCase {
    /**
     * Create a state
     *
     * @param request the create state request
     */
    void execute(CreateStateRequest request);
}
