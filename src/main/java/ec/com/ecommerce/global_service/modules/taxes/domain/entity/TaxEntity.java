package ec.com.ecommerce.global_service.modules.taxes.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.global_service.modules.countries.domain.entity.CountryEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}
