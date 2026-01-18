package ec.com.ecommerce.global_service.modules.cities.domain.usecases;

import ec.com.ecommerce.global_service.modules.cities.application.dtos.response.CityResponse;

import java.util.UUID;

public interface RetrieveCityUseCase {
    CityResponse execute(UUID id);
}

