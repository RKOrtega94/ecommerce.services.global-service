package ec.com.ecommerce.global_service.modules.taxes.domain.usecases;

import ec.com.ecommerce.global_service.modules.taxes.application.dtos.response.TaxResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * RetrieveTaxesUseCase
 * This interface defines the contract for retrieving taxes from the system.
 */
public interface RetrieveTaxesUseCase {
    /**
     * Executes the use case to retrieve a paginated list of taxes.
     *
     * @param params the parameters for filtering and pagination
     * @return a paginated list of tax responses
     */
    Page<TaxResponse> execute(Map<String, Object> params);
}