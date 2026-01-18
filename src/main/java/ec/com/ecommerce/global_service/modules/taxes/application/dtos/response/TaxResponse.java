package ec.com.ecommerce.global_service.modules.taxes.application.dtos.response;

import ec.com.ecommerce.enums.Status;

import java.util.UUID;

/**
 * TaxResponse
 *
 * @param id         tax id
 * @param name       tax name
 * @param percentage tax percentage
 * @param countryId  country id that this tax belongs to
 * @param status     tax status
 */
public record TaxResponse(UUID id, String name, Double percentage, UUID countryId, Status status) {
}