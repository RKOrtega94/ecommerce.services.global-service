package ec.com.ecommerce.global_service.modules.states.adapter.persistence;

import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.global_service.modules.states.application.ports.out.StateRepository;
import ec.com.ecommerce.global_service.modules.states.domain.entity.StateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static ec.com.ecommerce.global_service.modules.states.domain.specification.StateSpecification.stateSpecification;
import static ec.com.ecommerce.pagination.PaginationUtil.pageable;

@Repository
@RequiredArgsConstructor
public class StateRepositoryAdapter implements StateRepository {
    private final StateJpaRepository jpa;

    @Override
    public void save(StateEntity entity) {
        jpa.save(entity);
    }

    @Override
    public Optional<StateEntity> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Page<StateEntity> findAll(Map<String, Object> params) {
        return jpa.findAll(stateSpecification(params), pageable(params));
    }

    @Override
    public void delete(StateEntity entity) {
        entity.setStatus(Status.DELETED);
        entity.setDeletedAt(OffsetDateTime.now());
        jpa.save(entity);
    }
}
