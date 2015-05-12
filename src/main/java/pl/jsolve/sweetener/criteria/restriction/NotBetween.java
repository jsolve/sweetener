package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class NotBetween implements FieldRestriction {

    private final String fieldName;
    private final Number minValue;
    private final Number maxValue;
    private final boolean leftInclusive;
    private final boolean rightInclusive;

    public NotBetween(String fieldName, Number minValue, Number maxValue) {
        this.fieldName = fieldName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.leftInclusive = true;
        this.rightInclusive = true;
    }

    public NotBetween(String fieldName, Number minValue, Number maxValue, boolean leftInclusive, boolean rightInclusive) {
        this.fieldName = fieldName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.leftInclusive = leftInclusive;
        this.rightInclusive = rightInclusive;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public Number getMinValue() {
        return minValue;
    }

    public Number getMaxValue() {
        return maxValue;
    }

    public boolean isLeftInclusive() {
        return leftInclusive;
    }

    public boolean isRightInclusive() {
        return rightInclusive;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.LOW;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
        if (fieldValue != null) {
            if (!(fieldValue instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + fieldValue.getClass().getCanonicalName());
            }
            if (leftInclusive) {
                if (rightInclusive) {
                    return !(((Number) fieldValue).doubleValue() >= minValue.doubleValue() && ((Number) fieldValue)
                            .doubleValue() <= maxValue.doubleValue());
                } else {
                    return !(((Number) fieldValue).doubleValue() >= minValue.doubleValue() && ((Number) fieldValue)
                            .doubleValue() < maxValue.doubleValue());
                }

            } else {
                if (rightInclusive) {
                    return !(((Number) fieldValue).doubleValue() > minValue.doubleValue() && ((Number) fieldValue)
                            .doubleValue() <= maxValue.doubleValue());
                } else {
                    return !(((Number) fieldValue).doubleValue() > minValue.doubleValue() && ((Number) fieldValue)
                            .doubleValue() < maxValue.doubleValue());
                }
            }
        }
        return false;
    }
}
