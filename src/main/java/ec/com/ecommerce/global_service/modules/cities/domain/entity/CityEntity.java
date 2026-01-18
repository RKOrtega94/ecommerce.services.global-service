package ec.com.ecommerce.global_service.modules.cities.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.global_service.modules.states.domain.entity.StateEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class CityEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;
}

