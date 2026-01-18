package ec.com.ecommerce.global_service.modules.cities.adapter.rest;

import ec.com.ecommerce.models.ApiResponse;
import ec.com.ecommerce.models.PaginationResponse;
import ec.com.ecommerce.models.SuccessEmptyResponse;
import ec.com.ecommerce.models.SuccessResponse;
import ec.com.ecommerce.global_service.modules.cities.application.dtos.request.CreateCityRequest;
import ec.com.ecommerce.global_service.modules.cities.application.dtos.request.UpdateCityRequest;
import ec.com.ecommerce.global_service.modules.cities.application.dtos.response.CityResponse;
import ec.com.ecommerce.global_service.modules.cities.application.services.CityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
@Tag(name = "Cities", description = "Endpoints for managing cities")
public class CityController {
    private final CityService service;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_FORM_URLENCODED_VALUE}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createForm(@ModelAttribute @Valid CreateCityRequest request) {
        service.create(request);
        SuccessEmptyResponse response = SuccessEmptyResponse.created("City created successfully");
        return ResponseEntity.status(response.status()).body(response);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createJson(@RequestBody @Valid CreateCityRequest request) {
        service.create(request);
        SuccessEmptyResponse response = SuccessEmptyResponse.created("City created successfully");
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping(value = "/{id}", consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_FORM_URLENCODED_VALUE}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateForm(@PathVariable("id") UUID id, @ModelAttribute @Valid UpdateCityRequest request) {
        service.update(id, request);
        SuccessEmptyResponse response = SuccessEmptyResponse.ok("City updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> retrieve(@RequestParam Map<String, Object> params) {
        var data = service.retrieve(params);
        PaginationResponse<CityResponse> response = PaginationResponse.from(data, "Cities retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") UUID id) {
        var city = service.retrieve(id);
        SuccessResponse<CityResponse> response = SuccessResponse.ok(city, "City retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
