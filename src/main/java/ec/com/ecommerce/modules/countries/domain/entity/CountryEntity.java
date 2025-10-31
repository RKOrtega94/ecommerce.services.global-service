package ec.com.ecommerce.modules.countries.domain.entity;

import ec.com.ecommerce.entities.BaseEntity;
import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.modules.states.domain.entity.StateEntity;
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
@Table(name = "countries")
public class CountryEntity extends BaseEntity {
    private String name;
    private String code;
    @Column(name = "phone_code")
    private String phoneCode;
    private String currency;
    @Column(name = "currency_symbol")
    private String currencySymbol;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private Status status = Status.ACTIVE;

    @Builder.Default
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StateEntity> states = new HashSet<>();
}
