package ec.com.ecommerce.modules.states.application.ports.out;

import ec.com.ecommerce.modules.states.domain.entity.StateEntity;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * State repository port
 */
public interface StateRepository {
    /**
     * Save state entity
     *
     * @param entity state entity
     */
    void save(StateEntity entity);

    Optional<StateEntity> findById(UUID id);

    /**
     * Retrieve states
     *
     * @param params query params
     * @return a pagination data of states
     */
    Page<StateEntity> findAll(Map<String, Object> params);

    void delete(StateEntity entity);
}
