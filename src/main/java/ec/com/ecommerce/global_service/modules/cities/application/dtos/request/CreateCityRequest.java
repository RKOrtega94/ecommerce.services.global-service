package ec.com.ecommerce.global_service.modules.cities.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Create city request
 *
 * @param state the unique id of state
 * @param name  name of city
 */
public record CreateCityRequest(@NotNull UUID state, @NotNull @NotBlank @NotEmpty String name) {
}

