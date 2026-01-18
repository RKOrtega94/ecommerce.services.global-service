package ec.com.ecommerce.global_service.modules.countries.domain.usecases;

import ec.com.ecommerce.global_service.modules.countries.application.dtos.response.CountryResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface RetrieveCountriesUseCase {
    Page<CountryResponse> execute(Map<String, Object> params);
}
