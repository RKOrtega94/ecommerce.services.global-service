package ec.com.ecommerce.global_service.modules.countries.application.dtos.request;

import lombok.Builder;

/**
 * CreateCountryRequest
 *
 * @param name           country name
 * @param code           country code
 * @param phoneCode      country phone code
 * @param currency       country currency
 * @param currencySymbol country currency symbol
 */
@Builder
public record CreateCountryRequest(String name, String code, String phoneCode, String currency, String currencySymbol,
                                   String currencyCode, String flag) {
}

