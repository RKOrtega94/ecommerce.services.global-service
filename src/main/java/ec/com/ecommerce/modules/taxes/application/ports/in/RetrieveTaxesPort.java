package ec.com.ecommerce.modules.taxes.application.ports.in;

import ec.com.ecommerce.modules.taxes.application.dtos.response.TaxResponse;
import ec.com.ecommerce.modules.taxes.application.mapper.TaxMapper;
import ec.com.ecommerce.modules.taxes.application.ports.out.TaxRepository;
import ec.com.ecommerce.modules.taxes.domain.usecases.RetrieveTaxesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RetrieveTaxesPort implements RetrieveTaxesUseCase {
    private final TaxRepository repository;
    private final TaxMapper mapper;

    @Override
    public Page<TaxResponse> execute(Map<String, Object> params) {
        return repository.findAll(params).map(mapper::toResponse);
    }
}