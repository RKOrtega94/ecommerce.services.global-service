package ec.com.ecommerce.modules.countries.application.dtos.request;

import ec.com.ecommerce.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * CreateCountryRequest
 *
 * @param name           country name
 * @param code           country code
 * @param phoneCode      country phone code
 * @param currency       country currency
 * @param currencySymbol country currency symbol
 */
public record UpdateCountryRequest(@NotNull @NotEmpty @NotBlank @Length(min = 3, max = 150) String name,
                                   @NotNull @NotEmpty @NotBlank @Length(min = 2, max = 10) String code,
                                   @NotNull @NotEmpty @NotBlank @Length(min = 1, max = 10) @Pattern(regexp = "^[+]?\\d{1,10}$") String phoneCode,
                                   @NotNull @NotEmpty @NotBlank @Length(min = 3, max = 50) String currency,
                                   @NotNull @NotEmpty @NotBlank @Length(min = 1, max = 10) String currencySymbol,
                                   @NotNull Status status) {
}

