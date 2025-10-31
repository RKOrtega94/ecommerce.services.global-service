package ec.com.ecommerce.modules.countries.application.validators;

import ec.com.ecommerce.modules.countries.domain.usecases.RetrieveCountryByIdUseCase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator that checks if a country exists for the provided UUID.
 * It's implemented to be simple and use an injected use case. To allow
 * injection in a Jakarta/JavaEE/Spring environment, the validator is
 * written assuming the RetrieveCountryByIdUseCase will be obtained from
 * the CDI/Spring context via a static holder if necessary.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidCountryValidator implements ConstraintValidator<ValidCountry, UUID> {

    private boolean allowNull;

    private final RetrieveCountryByIdUseCase retrieveCountryByIdUseCase;

    @Override
    public void initialize(ValidCountry constraintAnnotation) {
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        if (value == null) return allowNull;
        if (retrieveCountryByIdUseCase == null) return true;
        try {
            return retrieveCountryByIdUseCase.execute(value) != null;
        } catch (Exception ex) {
            log.error("Error validating country with id {}: {}", value, ex.getMessage(), ex);
            return false;
        }
    }
}
