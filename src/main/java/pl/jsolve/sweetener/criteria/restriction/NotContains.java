package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;

import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class NotContains implements Restriction {

    private final String fieldName;
    private final boolean exactlyAllObjects;
    private final Object[] value;

    public NotContains(String fieldName, boolean exactlyAllObjects, Object[] value) {
	this.fieldName = fieldName;
	this.exactlyAllObjects = exactlyAllObjects;
	this.value = value;
    }

    @Override
    public String getFieldName() {
	return fieldName;
    }

    public boolean isExactlyAllObjects() {
	return exactlyAllObjects;
    }

    public Object[] getValue() {
	return value;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.LOW;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
	if (fieldValue == null) { // null indicates that collection does not
				  // contain any objects
	    return true;
	}
	if (!(fieldValue instanceof Collection)) {
	    throw new AccessToFieldException("Type mismatch. Expected Collection but was " + value.getClass().getCanonicalName());
	}
	Collection<?> fieldValueAsCollection = ((Collection<?>) fieldValue);
	int numberOfObject = 0;
	for (Object o : value) {
	    if (fieldValueAsCollection.contains(o)) {
		numberOfObject++;
	    }
	}
	return numberOfObject != value.length;
    }
}
