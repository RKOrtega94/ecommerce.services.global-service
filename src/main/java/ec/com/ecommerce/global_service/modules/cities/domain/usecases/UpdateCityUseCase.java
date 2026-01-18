package ec.com.ecommerce.global_service.modules.cities.domain.usecases;

import ec.com.ecommerce.global_service.modules.cities.application.dtos.request.UpdateCityRequest;

import java.util.UUID;

public interface UpdateCityUseCase {
    void execute(UUID id, UpdateCityRequest request);
}

