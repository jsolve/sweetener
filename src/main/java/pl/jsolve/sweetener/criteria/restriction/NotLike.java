package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class NotLike implements Restriction {

    private final String fieldName;
    private final Object value;
    private final boolean ignoreCase;

    public NotLike(String fieldName, Object value) {
	this.fieldName = fieldName;
	this.value = value;
	this.ignoreCase = false;
    }

    public NotLike(String fieldName, Object value, boolean ignoreCase) {
	this.fieldName = fieldName;
	this.value = value;
	this.ignoreCase = ignoreCase;
    }

    public String getFieldName() {
	return fieldName;
    }

    public Object getValue() {
	return value;
    }

    public boolean getIgnoreCase() {
	return ignoreCase;
    }

    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.MEDIUM;
    }

    public boolean satisfies(Object fieldValue) {
	if (fieldValue != null) {
	    if (!(fieldValue instanceof String)) {
		throw new AccessToFieldException("Type mismatch. Expected String but was " + value.getClass().getCanonicalName());
	    }
	    return !satisfiesString((String) fieldValue);
	}
	return false;
    }

    private boolean satisfiesString(String fieldValue) {
	if (ignoreCase) {
	    return fieldValue.toLowerCase().contains(((String) value).toLowerCase());
	} else {
	    return fieldValue.contains((String) value);
	}
    }

}
