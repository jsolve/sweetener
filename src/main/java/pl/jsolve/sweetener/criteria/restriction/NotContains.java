package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.restriction.CollectionExecutor.Executor;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class NotContains implements FieldRestriction {

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

        Contains contains = new Contains(fieldName, exactlyAllObjects, value);
        return !contains.satisfies(fieldValue);
    }

}
