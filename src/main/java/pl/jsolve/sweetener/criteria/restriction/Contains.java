package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.restriction.CollectionExecutor.Executor;

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

        CollectionExecutor executor = new CollectionExecutor();
        return executor.perform(fieldValue, new Executor() {

            @Override
            public boolean execute(Object[] elements) {
                if (exactlyAllObjects) {
                    return forExactlyAllObjects(elements);
                } else {
                    return forAnyObject(elements);
                }
            }

            @Override
            public boolean execute(Collection elements) {
                if (exactlyAllObjects) {
                    return forExactlyAllObjects(elements);
                } else {
                    return forAnyObject(elements);
                }
            }

            @Override
            public boolean execute(Map elements) {
                if (exactlyAllObjects) {
                    return forExactlyAllObjects(elements);
                } else {
                    return forAnyObject(elements);
                }
            }
        });

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

    private boolean forExactlyAllObjects(Map<?, ?> fieldValueAsMap) {
        Set<?> keySet = fieldValueAsMap.keySet();
        return forExactlyAllObjects(keySet);
    }

    private boolean forAnyObject(Map<?, ?> fieldValueAsMap) {
        Set<?> keySet = fieldValueAsMap.keySet();
        return forAnyObject(keySet);
    }
}
