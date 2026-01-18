package ec.com.ecommerce.global_service.modules.states.adapter.persistence;

import ec.com.ecommerce.global_service.modules.states.domain.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateJpaRepository extends JpaRepository<StateEntity, UUID>, PagingAndSortingRepository<StateEntity, UUID>, JpaSpecificationExecutor<StateEntity> {
}
