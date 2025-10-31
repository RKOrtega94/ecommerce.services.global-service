package ec.com.ecommerce.modules.taxes.domain.usecases;

import ec.com.ecommerce.modules.taxes.application.dtos.response.TaxResponse;

import java.util.UUID;

/**
 * RetrieveTaxByIdUseCase
 * This interface defines the contract for retrieving a specific tax by its ID.
 */
public interface RetrieveTaxByIdUseCase {
    /**
     * Executes the use case to retrieve a tax by its unique identifier.
     *
     * @param id the unique identifier of the tax to be retrieved
     * @return the tax response containing the tax details
     */
    TaxResponse execute(UUID id);
}