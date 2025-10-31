package ec.com.ecommerce.modules.states.domain.usecases;

import ec.com.ecommerce.modules.states.application.dtos.response.StateResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Retrieve states use case
 */
public interface RetrieveStatesUseCase {
    /**
     *
     * @param params query params
     * @return a pagination data of states
     */
    Page<StateResponse> execute(Map<String, Object> params);
}
