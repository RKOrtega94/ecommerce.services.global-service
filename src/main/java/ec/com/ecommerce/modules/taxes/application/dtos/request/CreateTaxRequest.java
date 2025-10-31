package ec.com.ecommerce.modules.taxes.application.dtos.request;

import ec.com.ecommerce.modules.countries.application.validators.ValidCountry;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

/**
 * CreateTaxRequest
 *
 * @param name       tax name
 * @param percentage tax percentage
 * @param country    country id that this tax belongs to
 */
public record CreateTaxRequest(@NotNull @NotEmpty @NotBlank @Length(min = 3, max = 100) String name,
                               @NotNull @Digits(integer = 3, fraction = 2) @DecimalMin("0.0") @DecimalMax("100.0") Double percentage,
                               @NotNull @ValidCountry UUID country) {
}