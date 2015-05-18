package pl.jsolve.sweetener.criteria.data;

import pl.jsolve.sweetener.criteria.restriction.CustomRestriction;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class StartsWithRestriction extends CustomRestriction {

    private String startsWith;

    public StartsWithRestriction(String fieldName, String startsWith) {
        super(fieldName);
        this.startsWith = startsWith;
    }

    @Override
    public boolean satisfies(Object fieldValue) {

        if (!(fieldValue instanceof String)) {
            throw new AccessToFieldException("Type mismatch. Expected String but was "
                    + fieldValue.getClass().getCanonicalName());
        }

        return ((String) fieldValue).startsWith(startsWith);
    }

}
