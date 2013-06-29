package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Greater implements Restriction {

    private final String fieldName;
    private final Number value;

    public Greater(String fieldName, Number value) {
	this.fieldName = fieldName;
	this.value = value;
    }

    public String getFieldName() {
	return fieldName;
    }

    public Number getValue() {
	return value;
    }

    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.LOW;
    }

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
