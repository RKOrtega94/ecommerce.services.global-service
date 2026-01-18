package ec.com.ecommerce.global_service.modules.states.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Create state request
 *
 * @param country the unique id of city
 * @param name    name of state
 */
public record CreateStateRequest(@NotNull UUID country, @NotNull @NotBlank @NotEmpty String name) {
}
