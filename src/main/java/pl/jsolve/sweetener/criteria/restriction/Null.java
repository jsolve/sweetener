package pl.jsolve.sweetener.criteria.restriction;

import pl.jsolve.sweetener.criteria.Restriction;

public class Null implements Restriction  {

    private final String fieldName;

    public Null(String fieldName) {
	this.fieldName = fieldName;
    }

    public String getFieldName() {
	return fieldName;
    }

    public RestrictionLevel getRestrictionLevel() {
	return RestrictionLevel.HIGH;
    }

    public boolean satisfies(Object fieldValue) {
	return fieldValue == null;
    }

}
