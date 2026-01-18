package ec.com.ecommerce.global_service.modules.cities.application.dtos.response;

import ec.com.ecommerce.enums.Status;

import java.util.UUID;

/**
 * City response
 *
 * @param id     the unique id of city
 * @param name   name of city
 * @param status status of city
 */
public record CityResponse(UUID id, String name, Status status) {
}

