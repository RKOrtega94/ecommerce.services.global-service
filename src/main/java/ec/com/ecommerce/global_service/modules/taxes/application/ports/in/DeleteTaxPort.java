package ec.com.ecommerce.global_service.modules.taxes.application.ports.in;

import ec.com.ecommerce.global_service.modules.taxes.application.ports.out.TaxRepository;
import ec.com.ecommerce.global_service.modules.taxes.domain.usecases.DeleteTaxUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTaxPort implements DeleteTaxUseCase {
    private final TaxRepository repository;

    @Override
    public void execute(UUID id) {
        repository.findById(id).ifPresentOrElse(
                repository::delete,
                () -> {
                    throw new IllegalArgumentException("Tax not found with id: " + id);
                }
        );
    }
}