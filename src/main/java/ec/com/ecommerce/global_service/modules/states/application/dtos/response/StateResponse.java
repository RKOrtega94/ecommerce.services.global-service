package ec.com.ecommerce.global_service.modules.states.application.dtos.response;

import ec.com.ecommerce.enums.Status;

import java.util.UUID;

/**
 * State response
 *
 * @param id     the unique id of state
 * @param name   name of state
 * @param status status of state
 */
public record StateResponse(UUID id, String name, Status status) {
}
