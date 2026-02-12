package ec.com.ecommerce.global_service.modules.countries.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.global_service.modules.states.domain.entity.StateEntity;
import ec.com.ecommerce.global_service.modules.taxes.domain.entity.TaxEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class CountryEntity extends BaseEntity {
    private String name;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Column(name = "phone_code")
    private String phoneCode;
    private String currency;
    @Size(max = 255)
    @Column(name = "currency_symbol")
    private String currencySymbol;
    @Size(max = 255)
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "flag")
    private String flag;

    @Builder.Default
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StateEntity> states = new HashSet<>();

    @OneToMany(mappedBy = "country")
    private Set<TaxEntity> taxes = new LinkedHashSet<>();
}
