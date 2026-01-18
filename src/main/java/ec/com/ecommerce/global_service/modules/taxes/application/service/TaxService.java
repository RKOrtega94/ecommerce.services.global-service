package ec.com.ecommerce.global_service.modules.taxes.application.service;

import ec.com.ecommerce.global_service.modules.taxes.application.dtos.request.CreateTaxRequest;
import ec.com.ecommerce.global_service.modules.taxes.application.dtos.request.UpdateTaxRequest;
import ec.com.ecommerce.global_service.modules.taxes.application.dtos.response.TaxResponse;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.UUID;

/**
 * TaxService
 */
public interface TaxService {
    /**
     * Save a new tax
     *
     * @param request the create tax request
     */
    void save(CreateTaxRequest request);

    /**
     * Update an existing tax
     *
     * @param id      the tax id
     * @param request the update tax request
     */
    void update(UUID id, UpdateTaxRequest request);

    /**
     * Retrieve taxes with pagination and filtering
     *
     * @param params the filtering and pagination parameters
     * @return a page of tax responses
     */
    Page<TaxResponse> retrieve(Map<String, Object> params);

    /**
     * Find a tax by its id
     *
     * @param id the tax id
     * @return the tax response
     */
    TaxResponse findById(UUID id);

    /**
     * Delete a tax by its id
     *
     * @param id the tax id
     */
    void delete(UUID id);
}