package ec.com.ecommerce.global_service.modules.taxes.application.ports.in;

import ec.com.ecommerce.global_service.modules.countries.application.ports.out.CountryRepository;
import ec.com.ecommerce.global_service.modules.taxes.application.dtos.request.CreateTaxRequest;
import ec.com.ecommerce.global_service.modules.taxes.application.mapper.TaxMapper;
import ec.com.ecommerce.global_service.modules.taxes.application.ports.out.TaxRepository;
import ec.com.ecommerce.global_service.modules.taxes.domain.entity.TaxEntity;
import ec.com.ecommerce.global_service.modules.taxes.domain.usecases.CreateTaxUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaxPort implements CreateTaxUseCase {
    private final TaxMapper mapper;
    private final TaxRepository repository;
    private final CountryRepository countryRepository;

    @Override
    public void execute(CreateTaxRequest request) {
        TaxEntity taxEntity = mapper.toEntity(request);
        countryRepository.findById(request.country()).ifPresentOrElse(countryEntity -> {
            taxEntity.setCountry(countryEntity);
            repository.save(taxEntity);
        }, () -> {
            throw new IllegalArgumentException("Country with id " + request.country() + " does not exist.");
        });
    }
}