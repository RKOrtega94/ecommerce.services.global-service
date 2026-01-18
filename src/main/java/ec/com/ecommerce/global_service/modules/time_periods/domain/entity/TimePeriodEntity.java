package ec.com.ecommerce.global_service.modules.time_periods.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.global_service.modules.time_periods.domain.enums.PeriodType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time_periods")
public class TimePeriodEntity extends BaseEntity {
    @Size(max = 100)
    @NotNull
    @Column(name = "label", nullable = false, length = 100)
    private String label;
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;
    @NotNull
    @Column(name = "period_type", nullable = false, length = Integer.MAX_VALUE)
    private PeriodType periodType;
    @NotNull
    @Column(name = "value", nullable = false)
    private Integer value;
}
