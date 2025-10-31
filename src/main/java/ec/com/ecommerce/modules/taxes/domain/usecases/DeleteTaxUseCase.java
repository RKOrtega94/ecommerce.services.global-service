package ec.com.ecommerce.modules.taxes.domain.usecases;

import java.util.UUID;

/**
 * DeleteTaxUseCase
 * This interface defines the contract for deleting a tax from the system.
 */
public interface DeleteTaxUseCase {
    /**
     * Executes the use case to delete a tax.
     *
     * @param id the unique identifier of the tax to be deleted
     */
    void execute(UUID id);
}