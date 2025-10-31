package ec.com.ecommerce.modules.taxes.adapter.rest;

import ec.com.ecommerce.models.ApiResponse;
import ec.com.ecommerce.models.PaginationResponse;
import ec.com.ecommerce.models.SuccessEmptyResponse;
import ec.com.ecommerce.models.SuccessResponse;
import ec.com.ecommerce.modules.taxes.application.dtos.request.CreateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.dtos.request.UpdateTaxRequest;
import ec.com.ecommerce.modules.taxes.application.dtos.response.TaxResponse;
import ec.com.ecommerce.modules.taxes.application.service.TaxService;
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
@RequestMapping("/api/v1/taxes")
@Tag(name = "Taxes", description = "Endpoints for managing taxes")
public class TaxController {
    private final TaxService service;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_FORM_URLENCODED_VALUE}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createForm(@ModelAttribute @Valid CreateTaxRequest request) {
        service.save(request);
        SuccessEmptyResponse response = SuccessEmptyResponse.created("Tax created successfully");
        return ResponseEntity.status(response.status()).body(response);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createJson(@RequestBody @Valid CreateTaxRequest request) {
        service.save(request);
        SuccessEmptyResponse response = SuccessEmptyResponse.created("Tax created successfully");
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping(value = "/{id}", consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_FORM_URLENCODED_VALUE}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateForm(@PathVariable("id") UUID id, @ModelAttribute @Valid UpdateTaxRequest request) {
        service.update(id, request);
        SuccessEmptyResponse response = SuccessEmptyResponse.ok("Tax updated successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateJson(@PathVariable("id") UUID id, @RequestBody @Valid UpdateTaxRequest request) {
        service.update(id, request);
        SuccessEmptyResponse response = SuccessEmptyResponse.ok("Tax updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> retrieve(@RequestParam Map<String, Object> params) {
        var taxes = service.retrieve(params);
        PaginationResponse<TaxResponse> response = PaginationResponse.from(taxes, "Taxes retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") UUID id) {
        var tax = service.findById(id);
        SuccessResponse<TaxResponse> response = SuccessResponse.ok(tax, "Tax retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}