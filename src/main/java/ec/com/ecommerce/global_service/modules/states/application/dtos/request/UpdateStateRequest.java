package ec.com.ecommerce.global_service.modules.states.application.dtos.request;

import ec.com.ecommerce.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Update state request
 *
 * @param country the unique id of country
 * @param name    name of state
 * @param status  status of state
 */
public record UpdateStateRequest(@NotNull UUID country, @NotNull @NotBlank @NotEmpty String name,
                                 @NotNull Status status) {
}
