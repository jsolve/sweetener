package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Greater implements FieldRestriction {

    private final String fieldName;
    private final Number value;

    public Greater(String fieldName, Number value) {
	this.fieldName = fieldName;
	this.value = value;
    }

    @Override
    public String getFieldName() {
	return fieldName;
    }

    public Number getValue() {
	return value;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.LOW;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
	if (fieldValue != null) {
	    if (!(fieldValue instanceof Number)) {
		throw new AccessToFieldException("Type mismatch. Expected Number but was " + fieldValue.getClass().getCanonicalName());
	    }
	    return ((Number) fieldValue).doubleValue() > value.doubleValue();
	}
	return false;
    }

}
