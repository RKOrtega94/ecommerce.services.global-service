package ec.com.ecommerce.modules.taxes.application.ports.in;

import ec.com.ecommerce.modules.countries.application.ports.out.CountryRepository;
import ec.com.ecommerce.modules.taxes.application.dtos.request.UpdateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.mapper.TaxMapper;
import ec.com.ecommerce.modules.taxes.application.ports.out.TaxRepository;
import ec.com.ecommerce.modules.taxes.domain.usecases.UpdateTaxUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTaxPort implements UpdateTaxUseCase {
    private final TaxMapper mapper;
    private final TaxRepository repository;
    private final CountryRepository countryRepository;

    @Override
    public void execute(UUID id, UpdateTaxRequest request) {
        repository.findById(id).ifPresentOrElse(taxEntity -> {
            mapper.update(taxEntity, request);
            if (request.country() != null)
                countryRepository.findById(request.country()).ifPresent(taxEntity::setCountry);
            repository.save(taxEntity);
        }, () -> {
            throw new IllegalArgumentException("Tax not found with id: " + id);
        });
    }
}