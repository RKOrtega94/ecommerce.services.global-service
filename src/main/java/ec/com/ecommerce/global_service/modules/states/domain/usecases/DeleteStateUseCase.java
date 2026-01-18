package ec.com.ecommerce.global_service.modules.states.domain.usecases;

import java.util.UUID;

/**
 * Delete state use case
 */
public interface DeleteStateUseCase {
    /**
     * Delete a state
     *
     * @param id the unique id of statef
     */
    void execute(UUID id);
}
