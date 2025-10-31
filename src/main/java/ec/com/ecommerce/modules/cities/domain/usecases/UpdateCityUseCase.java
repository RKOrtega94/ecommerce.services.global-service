package ec.com.ecommerce.modules.cities.domain.usecases;

import ec.com.ecommerce.modules.cities.application.dtos.request.UpdateCityRequest;

import java.util.UUID;

public interface UpdateCityUseCase {
    void execute(UUID id, UpdateCityRequest request);
}

