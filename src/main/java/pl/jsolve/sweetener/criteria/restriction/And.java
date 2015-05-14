package pl.jsolve.sweetener.criteria.restriction;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.criteria.ComplexRestriction;
import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.Restriction;

public class And implements ComplexRestriction {

    private List<Restriction> restrictions;

    public And(Restriction... restrictions) {
        this.restrictions = Collections.newArrayList(restrictions);
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.HIGH;
    }

    @Override
    public boolean satisfies(Object o) {
        // All restrictions have to satisfying the condition:
        for (Restriction restriction : restrictions) {

            if (restriction instanceof FieldRestriction) {
                Object fieldValue = Reflections.getFieldValue(o, ((FieldRestriction) restriction).getFieldName());
                if (!restriction.satisfies(fieldValue)) {
                    return false;
                }
            } else if (restriction instanceof ComplexRestriction) {
                if (!restriction.satisfies(o)) {
                    return false;
                }
            }
        }
        return true;
    }

}
