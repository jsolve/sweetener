package pl.jsolve.sweetener.criteria;

import java.util.ArrayList;
import java.util.List;

public class Criteria {

    private final List<Restriction> restrictions = new ArrayList<>();

    private Criteria() {
    }

    public static Criteria newCriteria() {
	return new Criteria();
    }

    public Criteria add(Restriction restriction) {
	restrictions.add(restriction);
	return this;
    }

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
