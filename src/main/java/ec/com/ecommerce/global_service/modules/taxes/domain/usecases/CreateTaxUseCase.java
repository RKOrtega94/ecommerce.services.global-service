package ec.com.ecommerce.global_service.modules.taxes.domain.usecases;

import ec.com.ecommerce.global_service.modules.taxes.application.dtos.request.CreateTaxRequest;

/**
 * CreateTaxUseCase
 * This interface defines the contract for creating a new tax in the system.
 */
public interface CreateTaxUseCase {
    /**
     * Executes the use case to create a new tax.
     *
     * @param request the request object containing the details of the tax to be created
     */
    void execute(CreateTaxRequest request);
}