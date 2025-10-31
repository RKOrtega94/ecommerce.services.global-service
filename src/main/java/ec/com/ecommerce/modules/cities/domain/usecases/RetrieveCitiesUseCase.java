package ec.com.ecommerce.modules.cities.domain.usecases;

import ec.com.ecommerce.modules.cities.application.dtos.response.CityResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface RetrieveCitiesUseCase {
    Page<CityResponse> execute(Map<String, Object> params);
}

