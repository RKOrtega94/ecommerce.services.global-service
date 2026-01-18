package ec.com.ecommerce.global_service.modules.cities.application.ports.in;

import ec.com.ecommerce.global_service.modules.cities.application.ports.out.CityRepository;
import ec.com.ecommerce.global_service.modules.cities.domain.usecases.DeleteCityUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCityPort implements DeleteCityUseCase {
    private final CityRepository repository;

    @Override
    public void execute(UUID id) {
        repository.findById(id).ifPresentOrElse(entity -> repository.delete(entity), () -> {
            throw new EntityNotFoundException("City with id " + id + " not found");
        });
    }
}

