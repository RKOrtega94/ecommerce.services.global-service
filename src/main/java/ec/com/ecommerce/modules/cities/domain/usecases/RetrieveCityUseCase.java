package ec.com.ecommerce.modules.cities.domain.usecases;

import ec.com.ecommerce.modules.cities.application.dtos.response.CityResponse;

import java.util.UUID;

public interface RetrieveCityUseCase {
    CityResponse execute(UUID id);
}

