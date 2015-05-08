package pl.jsolve.sweetener.criteria;

import java.util.Comparator;

public class RestrictionComparator implements Comparator<Restriction> {

	@Override
	public int compare(Restriction restriction1, Restriction restriction2) {
		Integer level1 = restriction1.getRestrictionLevel().level;
		Integer level2 = restriction2.getRestrictionLevel().level;
		return level2.compareTo(level1);
	}

}
