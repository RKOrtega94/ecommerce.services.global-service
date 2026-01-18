package ec.com.ecommerce.global_service.modules.taxes.application.dtos.request;

import ec.com.ecommerce.enums.Status;
import ec.com.ecommerce.global_service.modules.countries.application.validators.ValidCountry;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

/**
 * UpdateTaxRequest
 *
 * @param name       tax name
 * @param percentage tax percentage
 * @param country    country id that this tax belongs to
 * @param status     tax status
 */
public record UpdateTaxRequest(@NotNull @NotEmpty @NotBlank @Length(min = 3, max = 100) String name,
                               @NotNull @Digits(integer = 3, fraction = 2) @DecimalMin(value = "0.0") @DecimalMax(value = "100.0") Double percentage,
                               @NotNull @ValidCountry UUID country, @NotNull Status status) {
}