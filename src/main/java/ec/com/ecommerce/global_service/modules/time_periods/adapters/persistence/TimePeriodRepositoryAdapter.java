package ec.com.ecommerce.global_service.modules.time_periods.adapters.persistence;

import ec.com.ecommerce.global_service.modules.time_periods.application.ports.out.TimePeriodRepository;
import ec.com.ecommerce.global_service.modules.time_periods.domain.entity.TimePeriodEntity;
import ec.com.ecommerce.global_service.modules.time_periods.domain.projections.TimePeriodGrpcProjection;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static ec.com.ecommerce.global_service.modules.time_periods.domain.specification.TimePeriodSpecification.buildTimePeriodSpecification;
import static ec.com.ecommerce.pagination.PaginationUtil.pageable;

@Repository
public class TimePeriodRepositoryAdapter implements TimePeriodRepository {
    private final TimePeriodRepositoryJpa jpa;

    public TimePeriodRepositoryAdapter(TimePeriodRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Page<TimePeriodEntity> findAll(Map<String, Object> params) {
        var pageable = pageable(params);
        var spec = buildTimePeriodSpecification(params);
        return jpa.findAll(spec, pageable);
    }

    @Override
    public Optional<TimePeriodGrpcProjection> findByIdGrpcProjection(UUID id) {
        return jpa.findByIdGrpcProjection(id);
    }
}
