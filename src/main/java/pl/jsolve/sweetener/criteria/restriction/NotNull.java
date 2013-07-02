package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.Restriction;

public class NotNull implements Restriction {

    private final String fieldName;

    public NotNull(String fieldName) {
	this.fieldName = fieldName;
    }

    @Override
    public String getFieldName() {
	return fieldName;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.HIGH;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
	return fieldValue != null;
    }
}
