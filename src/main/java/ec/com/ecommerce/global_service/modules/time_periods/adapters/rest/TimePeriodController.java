package ec.com.ecommerce.global_service.modules.time_periods.adapters.rest;

import ec.com.ecommerce.models.ApiResponse;
import ec.com.ecommerce.models.PaginationResponse;
import ec.com.ecommerce.global_service.modules.time_periods.application.service.TimePeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/time-periods")
@Tag(name = "Time Periods", description = "Endpoints for managing time periods")
public class TimePeriodController {
    private final TimePeriodService service;

    public TimePeriodController(TimePeriodService service) {
        this.service = service;
    }

    /**
     * Retrieve time periods based on provided parameters.
     *
     * @param params a map of query parameters for filtering and pagination
     * @return a ResponseEntity containing the ApiResponse with retrieved time periods
     */
    @GetMapping
    @Operation(summary = "Retrieve Time Periods", description = "Retrieve time periods based on provided parameters.", parameters = {//
            @Parameter(name = "page", description = "Page number for pagination"), //
            @Parameter(name = "size", description = "Number of items per page"), //
            @Parameter(name = "sort", description = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported."), //
            @Parameter(name = "label", description = "Filter by label of the time period"), //
            @Parameter(name = "description", description = "Filter by description of the time period"), //
            @Parameter(name = "periodType", description = "Filter by type of the time period. Possible values: DAILY, WEEKLY, MONTHLY, YEARLY", example = "DAILY || ['DAILY','WEEKLY']"), //
            @Parameter(name = "status", description = "Filter by status of the time period. Possible values: ACTIVE, INACTIVE", example = "ACTIVE") //
    })
    public ResponseEntity<ApiResponse> retrieveTimePeriods(@RequestParam Map<String, Object> params) {
        var data = service.retrieve(params);
        var response = PaginationResponse.from(data, "Time periods retrieved successfully");
        return ResponseEntity.ok(response);
    }
}
