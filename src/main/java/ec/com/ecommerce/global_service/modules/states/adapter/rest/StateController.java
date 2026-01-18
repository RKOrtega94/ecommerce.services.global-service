package ec.com.ecommerce.global_service.modules.states.adapter.rest;

import ec.com.ecommerce.models.ApiResponse;
import ec.com.ecommerce.models.PaginationResponse;
import ec.com.ecommerce.models.SuccessEmptyResponse;
import ec.com.ecommerce.models.SuccessResponse;
import ec.com.ecommerce.global_service.modules.states.application.dtos.request.CreateStateRequest;
import ec.com.ecommerce.global_service.modules.states.application.dtos.request.UpdateStateRequest;
import ec.com.ecommerce.global_service.modules.states.application.dtos.response.StateResponse;
import ec.com.ecommerce.global_service.modules.states.application.services.StateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/states")
@Tag(name = "States", description = "Endpoints for managing states")
public class StateController {
    private final StateService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createForm(@ModelAttribute @Valid CreateStateRequest request) {
        service.create(request);
        SuccessEmptyResponse response = SuccessEmptyResponse.created("State created successfully");
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> retrieve(@RequestParam Map<String, Object> params) {
        if (!params.containsKey("countryId")) throw new IllegalArgumentException("countryId is required");
        var data = service.retrieve(params);
        PaginationResponse<StateResponse> response = PaginationResponse.from(data, "States retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") UUID id) {
        var state = service.retrieve(id);
        SuccessResponse<StateResponse> response = SuccessResponse.ok(state, "State retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateForm(@PathVariable("id") UUID id, @ModelAttribute @Valid UpdateStateRequest request) {
        service.update(id, request);
        SuccessEmptyResponse response = SuccessEmptyResponse.ok("State updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
