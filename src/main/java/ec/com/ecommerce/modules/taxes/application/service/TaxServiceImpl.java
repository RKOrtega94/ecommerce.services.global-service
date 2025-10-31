package ec.com.ecommerce.modules.taxes.application.service;

import ec.com.ecommerce.modules.taxes.application.dtos.request.CreateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.dtos.request.UpdateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.dtos.response.TaxResponse;
import ec.com.ecommerce.modules.taxes.domain.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    private final CreateTaxUseCase createTax;
    private final UpdateTaxUseCase updateTax;
    private final RetrieveTaxesUseCase retrieveTaxes;
    private final RetrieveTaxByIdUseCase retrieveTaxById;
    private final DeleteTaxUseCase deleteTax;

    @Override
    public void save(CreateTaxRequest request) {
        createTax.execute(request);
    }

    @Override
    public void update(UUID id, UpdateTaxRequest request) {
        updateTax.execute(id, request);
    }

    @Override
    public Page<TaxResponse> retrieve(Map<String, Object> params) {
        return retrieveTaxes.execute(params);
    }

    @Override
    public TaxResponse findById(UUID id) {
        return retrieveTaxById.execute(id);
    }

    @Override
    public void delete(UUID id) {
        deleteTax.execute(id);
    }
}