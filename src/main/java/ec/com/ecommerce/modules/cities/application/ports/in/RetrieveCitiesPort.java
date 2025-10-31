package ec.com.ecommerce.modules.cities.application.ports.in;

import ec.com.ecommerce.modules.cities.application.dtos.response.CityResponse;
import ec.com.ecommerce.modules.cities.application.mapper.CityMapper;
import ec.com.ecommerce.modules.cities.application.ports.out.CityRepository;
import ec.com.ecommerce.modules.cities.domain.usecases.RetrieveCitiesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RetrieveCitiesPort implements RetrieveCitiesUseCase {
    private final CityMapper mapper;
    private final CityRepository repository;

    @Override
    public Page<CityResponse> execute(Map<String, Object> params) {
        return repository.findAll(params).map(mapper::toResponse);
    }
}

