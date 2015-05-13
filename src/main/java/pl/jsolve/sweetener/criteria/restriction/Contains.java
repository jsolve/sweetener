package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Contains implements FieldRestriction {

    private final String fieldName;
    private final boolean exactlyAllObjects;
    private final Object[] value;

    public Contains(String fieldName, boolean exactlyAllObjects, Object[] value) {
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
        if (fieldValue == null) {
            return false;
        }
        if (!(fieldValue instanceof Collection) && !(fieldValue.getClass().isArray())) {
            throw new AccessToFieldException("Type mismatch. Expected Collection or Array but was "
                    + value.getClass().getCanonicalName());
        }
        if (fieldValue instanceof Collection) {
            Collection<?> fieldValueAsCollection = ((Collection<?>) fieldValue);
            if (exactlyAllObjects) {
                return forExactlyAllObjects(fieldValueAsCollection);
            }
            return forAnyObject(fieldValueAsCollection);
        } else if (fieldValue.getClass().isArray()) {
            Object[] fieldValueAsArray = (Object[]) fieldValue;
            if (exactlyAllObjects) {
                return forExactlyAllObjects(fieldValueAsArray);
            }
            return forAnyObject(fieldValueAsArray);
        }
        return false;
    }

    private boolean forAnyObject(Object[] fieldValueAsArray) {
        for (Object o : value) {
            for (int i = 0; i < fieldValueAsArray.length; i++) {
                if (fieldValueAsArray[i].equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean forExactlyAllObjects(Object[] fieldValueAsArray) {
        for (Object o : value) {
            for (int i = 0; i < fieldValueAsArray.length; i++) {
                if (!fieldValueAsArray[i].equals(o)) {
                    return false;
                }
            }
        }
        return true;
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
