package pl.jsolve.sweetener.criteria.restriction;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.criteria.ComplexRestriction;
import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.Restriction;

public class OrRestriction implements ComplexRestriction {
    // Dodac IN, BETWEEN do restrykcji

    private List<Restriction> restrictions;

    public OrRestriction(Restriction... restrictions) {
        this.restrictions = Collections.newArrayList(restrictions);
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.HIGH;
    }

    @Override
    public boolean satisfies(Object o) {
        // Find the first restriction satisfying the condition:
        for (Restriction restriction : restrictions) {

            if (restriction instanceof FieldRestriction) {
                Object fieldValue = Reflections.getFieldValue(o, ((FieldRestriction) restriction).getFieldName());
                if (restriction.satisfies(fieldValue)) {
                    return true;
                }
            } else if (restriction instanceof ComplexRestriction) {
                if (restriction.satisfies(o)) {
                    return true;
                }
            }
        }
        return false;
    }

}
