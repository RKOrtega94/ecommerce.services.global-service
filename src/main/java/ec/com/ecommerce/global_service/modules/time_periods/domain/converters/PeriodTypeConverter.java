package ec.com.ecommerce.global_service.modules.time_periods.domain.converters;

import ec.com.ecommerce.global_service.modules.time_periods.domain.enums.PeriodType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter to map PeriodType enum to its String representation in the database and vice versa.
 */
@Converter(autoApply = true)
public class PeriodTypeConverter implements AttributeConverter<PeriodType, String> {
    /**
     * Converts the PeriodType enum to its String representation for database storage.
     *
     * @param attribute the entity attribute value to be converted
     * @return the corresponding String representation for the database column
     */
    @Override
    public String convertToDatabaseColumn(PeriodType attribute) {
        return attribute == null ? null : attribute.name();
    }

    /**
     * Converts the String representation from the database back to the PeriodType enum.
     *
     * @param dbData the data from the database column to be converted
     * @return the corresponding PeriodType enum value
     */
    @Override
    public PeriodType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : PeriodType.valueOf(dbData);
    }
}
