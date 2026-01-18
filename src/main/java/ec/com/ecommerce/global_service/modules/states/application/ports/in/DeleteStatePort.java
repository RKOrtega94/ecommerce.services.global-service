package ec.com.ecommerce.global_service.modules.states.application.ports.in;

import ec.com.ecommerce.global_service.modules.states.application.ports.out.StateRepository;
import ec.com.ecommerce.global_service.modules.states.domain.usecases.DeleteStateUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Delete state port
 */
@Service
@RequiredArgsConstructor
public class DeleteStatePort implements DeleteStateUseCase {
    private final StateRepository repository;

    @Override
    public void execute(UUID id) {
        repository.findById(id).ifPresentOrElse(repository::delete, () -> {
            throw new EntityNotFoundException("State with id " + id + " does not exist");
        });
    }
}
