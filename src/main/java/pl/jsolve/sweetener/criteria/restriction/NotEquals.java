package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class NotEquals implements Restriction {

    private final String fieldName;
    private final Object value;
    private final boolean ignoreCase;

    public NotEquals(String fieldName, Object value) {
	this.fieldName = fieldName;
	this.value = value;
	this.ignoreCase = false;
    }

    public NotEquals(String fieldName, Object value, boolean ignoreCase) {
	this.fieldName = fieldName;
	this.value = value;
	this.ignoreCase = ignoreCase;
    }

    @Override
    public String getFieldName() {
	return fieldName;
    }

    public Object getValue() {
	return value;
    }

    public boolean getIgnoreCase() {
	return ignoreCase;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.MEDIUM;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
	if (fieldValue != null) {
	    if (fieldValue instanceof String) {
		return satisfiesString((String) fieldValue);
	    }
	    return !fieldValue.equals(value);
	}
	return false;
    }

    private boolean satisfiesString(String fieldValue) {
	if (!(value instanceof String)) {
	    throw new AccessToFieldException("Type mismatch. Expected String but was " + value.getClass().getCanonicalName());
	}
	if (ignoreCase) {
	    return !fieldValue.equalsIgnoreCase((String) value);
	}
	return !fieldValue.equals(value);
    }
}
