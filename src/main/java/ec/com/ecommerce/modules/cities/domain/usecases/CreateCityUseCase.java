package ec.com.ecommerce.modules.cities.domain.usecases;

import ec.com.ecommerce.modules.cities.application.dtos.request.CreateCityRequest;

public interface CreateCityUseCase {
    void execute(CreateCityRequest request);
}

