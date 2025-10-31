package ec.com.ecommerce.modules.cities.application.ports.in;

import ec.com.ecommerce.modules.cities.application.dtos.response.CityResponse;
import ec.com.ecommerce.modules.cities.application.mapper.CityMapper;
import ec.com.ecommerce.modules.cities.application.ports.out.CityRepository;
import ec.com.ecommerce.modules.cities.domain.usecases.RetrieveCityUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RetrieveCityPort implements RetrieveCityUseCase {
    private final CityMapper mapper;
    private final CityRepository repository;

    @Override
    public CityResponse execute(UUID id) {
        return repository.findById(id).map(mapper::toResponse).orElseThrow(() -> new EntityNotFoundException("City with id " + id + " not found"));
    }
}

