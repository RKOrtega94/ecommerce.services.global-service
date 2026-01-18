package ec.com.ecommerce.global_service.modules.cities.domain.usecases;

import ec.com.ecommerce.global_service.modules.cities.application.dtos.request.CreateCityRequest;

public interface CreateCityUseCase {
    void execute(CreateCityRequest request);
}

