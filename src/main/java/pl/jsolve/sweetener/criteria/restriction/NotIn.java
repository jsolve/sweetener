package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.FieldRestriction;

public class NotIn implements FieldRestriction {

    private final String fieldName;
    private final Object[] values;
    private boolean containsNull;

    public NotIn(String fieldName, Object[] values) {
        this.fieldName = fieldName;
        this.values = values;
        for (Object o : values) {
            if (o == null) {
                containsNull = true;
            }
        }
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public Object[] getValue() {
        return values;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.LOW;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
        if (fieldValue == null) {
            if (containsNull) {
                return false;
            }
            return true;
        }

        for (Object o : values) {
            if (fieldValue.equals(o)) {
                return false;
            }
        }
        return true;
    }

}
