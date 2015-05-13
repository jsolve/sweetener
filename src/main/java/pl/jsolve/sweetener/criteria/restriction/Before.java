package pl.jsolve.sweetener.criteria.restriction;

import java.util.Date;
import java.util.Map;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.exception.InvalidArgumentException;

public class Before implements FieldRestriction {

    private final String fieldName;
    private final Date value;
    private final Map<Class<?>, DateExtractor> dateExtractors;

    public Before(String fieldName, Object date, Map<Class<?>, DateExtractor> dateExtractors) {
        this.fieldName = fieldName;
        this.dateExtractors = dateExtractors;
        if (date instanceof Date) {
            this.value = (Date) date;
        } else if (dateExtractors.containsKey(date.getClass())) {
            this.value = extractDate(date);
        } else {
            throw new InvalidArgumentException("Cannot find date extractor for class "
                    + date.getClass().getCanonicalName());
        }
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public Date getValue() {
        return value;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.LOW;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
        if (fieldValue != null) {
            if (fieldValue instanceof Date) {
                return isBefore((Date) fieldValue, value);
            } else if (dateExtractors.containsKey(fieldValue.getClass())) {
                Date extractedDate = extractDate(fieldValue);
                return isBefore(extractedDate, value);
            }

        }
        return false;
    }

    private Date extractDate(Object fieldValue) {
        DateExtractor dateExtractor = dateExtractors.get(fieldValue.getClass());
        return dateExtractor.extract(fieldValue);
    }

    private boolean isBefore(Date extractedValue, Date threshold) {
        if (threshold.getTime() > extractedValue.getTime()) {
            return true;
        } else {
            return false;
        }
    }

}
