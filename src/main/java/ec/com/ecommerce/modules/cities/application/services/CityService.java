package ec.com.ecommerce.modules.cities.application.services;

import ec.com.ecommerce.modules.cities.application.dtos.request.CreateCityRequest;
import ec.com.ecommerce.modules.cities.application.dtos.request.UpdateCityRequest;
import ec.com.ecommerce.modules.cities.application.dtos.response.CityResponse;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.UUID;

public interface CityService {
    void create(CreateCityRequest request);

    void update(UUID id, UpdateCityRequest request);

    CityResponse retrieve(UUID id);

    Page<CityResponse> retrieve(Map<String, Object> params);

    void delete(UUID id);
}

