package ec.com.ecommerce.modules.taxes.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.modules.countries.domain.entity.CountryEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taxes")
public class TaxEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status = Status.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}
