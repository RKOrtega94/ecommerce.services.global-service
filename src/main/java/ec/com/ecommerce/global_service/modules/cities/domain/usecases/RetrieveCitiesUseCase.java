package ec.com.ecommerce.global_service.modules.cities.domain.usecases;

import ec.com.ecommerce.global_service.modules.cities.application.dtos.response.CityResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface RetrieveCitiesUseCase {
    Page<CityResponse> execute(Map<String, Object> params);
}

