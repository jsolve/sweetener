package pl.jsolve.sweetener.criteria;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.criteria.restriction.AndRestriction;
import pl.jsolve.sweetener.criteria.restriction.OrRestriction;

public class Criteria {

    private final List<Restriction> restrictions = Collections.newArrayList();

    private Criteria() {
    }

    public static Criteria newCriteria() {
        return new Criteria();
    }

    public Criteria add(Restriction restriction) {
        restrictions.add(restriction);
        return this;
    }

  /*  public Criteria or(Restriction... restrictions) {
        this.restrictions.add(new OrRestriction(restrictions));
        return this;
    }

    public Criteria and(Restriction... restrictions) {
        this.restrictions.add(new AndRestriction(restrictions));
        return this;
    }
*/
    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public List<Restriction> getSortedRestrictions() {
        sortByRestrictionLevel();
        return restrictions;
    }

    private void sortByRestrictionLevel() {
        java.util.Collections.sort(restrictions, new RestrictionComparator());
    }
}
