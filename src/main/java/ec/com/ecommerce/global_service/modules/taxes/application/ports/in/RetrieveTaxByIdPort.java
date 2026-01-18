package ec.com.ecommerce.global_service.modules.taxes.application.ports.in;

import ec.com.ecommerce.global_service.modules.taxes.application.dtos.response.TaxResponse;
import ec.com.ecommerce.global_service.modules.taxes.application.mapper.TaxMapper;
import ec.com.ecommerce.global_service.modules.taxes.application.ports.out.TaxRepository;
import ec.com.ecommerce.global_service.modules.taxes.domain.usecases.RetrieveTaxByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RetrieveTaxByIdPort implements RetrieveTaxByIdUseCase {
    private final TaxRepository repository;
    private final TaxMapper mapper;

    @Override
    public TaxResponse execute(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Tax not found with id: " + id));
    }
}