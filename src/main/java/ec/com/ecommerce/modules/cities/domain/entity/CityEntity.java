package ec.com.ecommerce.modules.cities.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.modules.states.domain.entity.StateEntity;
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

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status = Status.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;
}

