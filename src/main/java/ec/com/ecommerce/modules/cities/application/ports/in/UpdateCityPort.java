package ec.com.ecommerce.modules.cities.application.ports.in;

import ec.com.ecommerce.modules.cities.application.dtos.request.UpdateCityRequest;
import ec.com.ecommerce.modules.cities.application.mapper.CityMapper;
import ec.com.ecommerce.modules.cities.application.ports.out.CityRepository;
import ec.com.ecommerce.modules.cities.domain.usecases.UpdateCityUseCase;
import ec.com.ecommerce.modules.states.application.ports.out.StateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCityPort implements UpdateCityUseCase {
    private final CityMapper mapper;
    private final CityRepository repository;
    private final StateRepository stateRepository;

    @Override
    public void execute(UUID id, UpdateCityRequest request) {
        stateRepository.findById(request.state()).ifPresentOrElse(stateEntity -> repository.findById(id).ifPresentOrElse(entity -> {
            mapper.update(entity, request);
            entity.setState(stateEntity);
            repository.save(entity);
        }, () -> {
            throw new EntityNotFoundException("City with id " + id + " not found");
        }), () -> {
            throw new EntityNotFoundException("State with id " + request.state() + " does not exist");
        });
    }
}

