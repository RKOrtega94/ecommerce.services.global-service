package ec.com.ecommerce.modules.states.application.ports.in;

import ec.com.ecommerce.modules.countries.application.ports.out.CountryRepository;
import ec.com.ecommerce.modules.states.application.dtos.request.CreateStateRequest;
import ec.com.ecommerce.modules.states.application.mapper.StateMapper;
import ec.com.ecommerce.modules.states.application.ports.out.StateRepository;
import ec.com.ecommerce.modules.states.domain.usecases.CreateStateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateStatePort implements CreateStateUseCase {
    private final StateMapper mapper;
    private final StateRepository repository;
    private final CountryRepository countryRepository;

    @Override
    public void execute(CreateStateRequest request) {
        countryRepository.findById(request.country()).ifPresentOrElse(country -> {
            var entity = mapper.toEntity(request);
            entity.setCountry(country);
            repository.save(entity);
        }, () -> {
            throw new IllegalArgumentException("Country with id " + request.country() + " does not exist");
        });
    }
}
