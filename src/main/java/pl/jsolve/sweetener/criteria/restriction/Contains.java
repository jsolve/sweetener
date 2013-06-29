package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;

import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Contains implements Restriction {

    private final String fieldName;
    private final boolean exactlyAllObjects;
    private final Object[] value;

    public Contains(String fieldName, boolean exactlyAllObjects, Object[] value) {
	this.fieldName = fieldName;
	this.exactlyAllObjects = exactlyAllObjects;
	this.value = value;
    }

    public String getFieldName() {
	return fieldName;
    }

    public boolean isExactlyAllObjects() {
	return exactlyAllObjects;
    }

    public Object[] getValue() {
	return value;
    }

    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.LOW;
    }

    public boolean satisfies(Object fieldValue) {
	if (fieldValue == null) {
	    return false;
	}
	if (!(fieldValue instanceof Collection)) {
	    throw new AccessToFieldException("Type mismatch. Expected Collection but was " + value.getClass().getCanonicalName());
	}
	Collection<?> fieldValueAsCollection = ((Collection<?>) fieldValue);
	if (exactlyAllObjects) {
	    return forExactlyAllObjects(fieldValueAsCollection);
	} else {
	    return forAnyObject(fieldValueAsCollection);
	}
    }

    private boolean forExactlyAllObjects(Collection<?> fieldValueAsCollection) {
	for (Object o : value) {
	    if (!fieldValueAsCollection.contains(o)) {
		return false;
	    }
	}
	return true;
    }

    private boolean forAnyObject(Collection<?> fieldValueAsCollection) {
	for (Object o : value) {
	    if (fieldValueAsCollection.contains(o)) {
		return true;
	    }
	}
	return false;
    }
}
