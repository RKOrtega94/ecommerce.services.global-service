package ec.com.ecommerce.modules.states.application.ports.in;

import ec.com.ecommerce.modules.countries.application.ports.out.CountryRepository;
import ec.com.ecommerce.modules.states.application.dtos.request.UpdateStateRequest;
import ec.com.ecommerce.modules.states.application.mapper.StateMapper;
import ec.com.ecommerce.modules.states.application.ports.out.StateRepository;
import ec.com.ecommerce.modules.states.domain.usecases.UpdateStateUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateStatePort implements UpdateStateUseCase {
    private final StateMapper mapper;
    private final StateRepository repository;
    private final CountryRepository countryRepository;

    @Override
    public void execute(UUID id, UpdateStateRequest request) {
        countryRepository.findById(request.country()).ifPresentOrElse(countryEntity -> repository.findById(id).ifPresentOrElse(entity -> {
            mapper.update(entity, request);
            entity.setCountry(countryEntity);
            repository.save(entity);
        }, () -> {
            throw new EntityNotFoundException("State with id " + id + " not found");
        }), () -> {
            throw new EntityNotFoundException("Country with id " + request.country() + " does not exist");
        });
    }
}
