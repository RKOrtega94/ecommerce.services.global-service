package ec.com.ecommerce.global_service.modules.cities.application.services;

import ec.com.ecommerce.global_service.modules.cities.application.dtos.request.CreateCityRequest;
import ec.com.ecommerce.global_service.modules.cities.application.dtos.request.UpdateCityRequest;
import ec.com.ecommerce.global_service.modules.cities.application.dtos.response.CityResponse;
import ec.com.ecommerce.global_service.modules.cities.domain.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final RetrieveCitiesUseCase retrieveCities;
    private final RetrieveCityUseCase retrieveCity;
    private final UpdateCityUseCase updateCity;
    private final CreateCityUseCase createCity;
    private final DeleteCityUseCase deleteCity;

    @Override
    public void create(CreateCityRequest request) {
        createCity.execute(request);
    }

    @Override
    public void update(UUID id, UpdateCityRequest request) {
        updateCity.execute(id, request);
    }

    @Override
    public CityResponse retrieve(UUID id) {
        return retrieveCity.execute(id);
    }

    @Override
    public Page<CityResponse> retrieve(Map<String, Object> params) {
        return retrieveCities.execute(params);
    }

    @Override
    public void delete(UUID id) {
        deleteCity.execute(id);
    }
}

