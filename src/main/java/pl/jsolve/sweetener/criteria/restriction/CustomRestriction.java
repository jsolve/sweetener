package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.FieldRestriction;

public abstract class CustomRestriction implements FieldRestriction {

    private final String fieldName;

    public CustomRestriction(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.MEDIUM;
    }
}
