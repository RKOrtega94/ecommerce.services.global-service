package ec.com.ecommerce.modules.taxes.domain.usecases;

import ec.com.ecommerce.modules.taxes.application.dtos.request.UpdateTaxRequest;

import java.util.UUID;

/**
 * UpdateTaxUseCase
 * This interface defines the contract for updating an existing tax in the system.
 */
public interface UpdateTaxUseCase {
    /**
     * Executes the use case to update an existing tax.
     *
     * @param id      the unique identifier of the tax to be updated
     * @param request the request object containing the new details for the tax
     */
    void execute(UUID id, UpdateTaxRequest request);
}