package ec.com.ecommerce.global_service.modules.time_periods.application.dtos.response;

import ec.com.ecommerce.global_service.modules.time_periods.domain.enums.PeriodType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.UUID;

@Schema(description = "DTO representing a time period response.")
public record TimePeriodResponse(
        @Schema(description = "The unique identifier of the time period.", example = "123e4567-e89b-12d3-a456-426614174000")

        UUID id,

        @Schema(description = "The label of the time period.", example = "One week")

        String label,

        @Schema(description = "The description of the time period.", example = "Time period from 8 AM to 12 PM")

        String description,

        @Schema(description = "The type of the time period.", example = "WEEKLY")

        PeriodType periodType,

        @Schema(description = "The integer value associated with the time period.", example = "7")

        Integer value) implements Serializable {
    private static final long serialVersionUID = 1L;
}
