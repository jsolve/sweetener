package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.FieldRestriction;

public class In implements FieldRestriction {

    private final String fieldName;
    private final Object[] values;
    private boolean containsNull;

    public In(String fieldName, Object[] values) {
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
                return true;
            }
            return false;
        }

        for (Object o : values) {
            if (fieldValue.equals(o)) {
                return true;
            }
        }
        return false;
    }

}
