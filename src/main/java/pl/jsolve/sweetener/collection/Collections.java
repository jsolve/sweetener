package pl.jsolve.sweetener.collection;

import java.util.Collection;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.criteria.Criteria;
import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.InvalidArgumentException;

public class Collections {

	public static <T> Collection<T> filter(Collection<T> collection, Criteria criteria) {
		Collection<T> result = createNewInstanceOfCollection(collection.getClass());

		for (T t : collection) {
			if (checkIfElementSatisfiesConditions(t, criteria)) {
				result.add(t);
			}
		}
		return result;
	}

	public static <T> T truncate(Collection<?> collection, int to) {
		return truncate(collection, 0, to);
	}

	@SuppressWarnings("unchecked")
	public static <T> T truncate(Collection<?> collection, int from, int to) {
		int countOfElements = collection.size();
		if (to < 0) {
			to = countOfElements + to;
		}
		if (from < 0) {
			throw new InvalidArgumentException("The 'From' value cannot be negative");
		}
		if (from > countOfElements - 1) {
			throw new InvalidArgumentException("The 'From' value cannot be greater than size of collection");
		}
		if (from > to) {
			throw new InvalidArgumentException("The 'From' value cannot be greater than the 'to' value");
		}
		if (to > countOfElements - 1) {
			throw new InvalidArgumentException("The 'To' value cannot be greater than size of collection");
		}
		Collection<T> result = createNewInstanceOfCollection(collection.getClass());
		Object[] array = collection.toArray();
		for (int i = from; i <= to; i++) {
			result.add((T) array[i]);
		}
		return (T) result;
	}

	@SuppressWarnings("unchecked")
	private static <T> Collection<T> createNewInstanceOfCollection(Class<?> clazz) {
		try {
			return (Collection<T>) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
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

	public static <T> Pagination<T> paginate(Collection<T> collection, int page, int resultsPerPage) {
		int totalElements = collection.size();
		int from = page * resultsPerPage;
		int to = from + resultsPerPage;
		to = to > totalElements ? totalElements : to;
		Collection<T> elementsOfPage = truncate(collection, from, to - 1);
		return new Pagination<T>(page, resultsPerPage, totalElements, elementsOfPage);
	}
}
