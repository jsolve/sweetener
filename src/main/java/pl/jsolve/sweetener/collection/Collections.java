package pl.jsolve.sweetener.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.criteria.Criteria;
import pl.jsolve.sweetener.criteria.Restriction;

public class Collections {

    public static <T> Collection<T> filter(Collection<T> collection, Criteria criteria) {
	List<T> result = new ArrayList<>();
	for (T t : collection) {
	    if (checkIfElementSatisfiesConditions(t, criteria)) {
		result.add(t);
	    }
	}
	return result;
    }

    private static boolean checkIfElementSatisfiesConditions(Object o, Criteria criteria) {
	for (Restriction restriction : criteria.getSortedRestrictions()) {
	    Object fieldValue = Reflections.getFieldValue(o, restriction.getFieldName());
	    if (!restriction.satisfies(fieldValue)) {
		return false;
	    }
	}
	return true;
    }

}
