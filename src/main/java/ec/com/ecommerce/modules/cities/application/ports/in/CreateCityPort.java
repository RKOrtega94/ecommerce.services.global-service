package ec.com.ecommerce.modules.cities.application.ports.in;

import ec.com.ecommerce.modules.cities.application.dtos.request.CreateCityRequest;
import ec.com.ecommerce.modules.cities.application.mapper.CityMapper;
import ec.com.ecommerce.modules.cities.application.ports.out.CityRepository;
import ec.com.ecommerce.modules.cities.domain.usecases.CreateCityUseCase;
import ec.com.ecommerce.modules.states.application.ports.out.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCityPort implements CreateCityUseCase {
    private final CityMapper mapper;
    private final CityRepository repository;
    private final StateRepository stateRepository;

    @Override
    public void execute(CreateCityRequest request) {
        stateRepository.findById(request.state()).ifPresentOrElse(state -> {
            var entity = mapper.toEntity(request);
            entity.setState(state);
            repository.save(entity);
        }, () -> {
            throw new IllegalArgumentException("State with id " + request.state() + " does not exist");
        });
    }
}

