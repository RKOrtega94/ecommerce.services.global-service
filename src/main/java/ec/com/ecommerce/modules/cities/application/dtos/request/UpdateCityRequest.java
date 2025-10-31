package ec.com.ecommerce.modules.cities.application.dtos.request;

import ec.com.ecommerce.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Update city request
 *
 * @param state  the unique id of state
 * @param name   name of city
 * @param status status of city
 */
public record UpdateCityRequest(@NotNull UUID state, @NotNull @NotBlank @NotEmpty String name,
                                @NotNull Status status) {
}

