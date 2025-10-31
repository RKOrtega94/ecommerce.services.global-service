package ec.com.ecommerce.modules.states.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.modules.cities.domain.entity.CityEntity;
import ec.com.ecommerce.modules.countries.domain.entity.CountryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "states")
public class StateEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status = Status.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Builder.Default
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CityEntity> cities = new HashSet<>();
}
