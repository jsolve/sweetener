package pl.jsolve.sweetener.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.criteria.Criteria;
import pl.jsolve.sweetener.criteria.Restriction;
import pl.jsolve.sweetener.exception.InvalidArgumentException;

public final class Collections {

	private Collections() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	public static <T> Collection<T> filter(Collection<T> collection, Criteria criteria) {
		Collection<T> result = createNewInstanceOfCollection(collection.getClass());

		for (T t : collection) {
			if (checkIfElementSatisfiesConditions(t, criteria)) {
				result.add(t);
			}
		}
		return result;
	}

	public static <T extends Collection<E>, E> T truncate(T collection, int to) {
		return truncate(collection, 0, to);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Collection<E>, E> T truncate(T collection, int from, int to) {
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
		T result = (T) createNewInstanceOfCollection(collection.getClass());
		Object[] array = collection.toArray();
		for (int i = from; i <= to; i++) {
			result.add((E) array[i]);
		}
		return (T) result;
	}

	public static <T extends Collection<E>, E> T createNewInstanceOfCollection(Class<T> clazz) {
		try {
			return clazz.newInstance();
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

	@SuppressWarnings("unchecked")
	public static <T> Pagination<T> paginate(Collection<T> collection, int page, int resultsPerPage) {
		int totalElements = collection.size();
		int from = page * resultsPerPage;
		int to = getTo(resultsPerPage, totalElements, from);
		Collection<T> elementsOfPage = createNewInstanceOfCollection(collection.getClass());
		if(from < collection.size()) {
			elementsOfPage = truncate(collection, from, to);
		}
		return new Pagination<>(page, resultsPerPage, totalElements, elementsOfPage);
	}

	public static <T> ChoppedElements<T> chopElements(Collection<T> collection, int resultsPerPage) {
		int totalElements = collection.size();
		int numberOfPages = (totalElements + resultsPerPage - 1) / resultsPerPage;
		List<Collection<T>> listOfPages = new ArrayList<>();
		for (int i = 0; i < numberOfPages; i++) {
			Collection<T> elementsOfPage = truncate(collection, i * resultsPerPage,
					getTo(resultsPerPage, totalElements, i * resultsPerPage));
			listOfPages.add(elementsOfPage);
		}
		return new ChoppedElements<>(0, resultsPerPage, totalElements, listOfPages);
	}

	private static int getTo(int resultsPerPage, int totalElements, int from) {
		int to = from + resultsPerPage;
		to = to > totalElements - 1 ? totalElements : to;
		return to - 1;
	}

	public static <E> Map<GroupKey, List<E>> group(Collection<E> collection, String ... properties) {
		Map<GroupKey, List<E>> map = new HashMap<>();

		// prepare map of duplicates
		for (E element : collection) {
			Object[] fieldValues = getFieldsValues(element, properties);
			GroupKey groupKey = new GroupKey(fieldValues);
			if (map.containsKey(groupKey)) {
				map.get(groupKey).add(element);
			} else {
				List<E> list = new ArrayList<>();
				list.add(element);
				map.put(groupKey, list);
			}
		}
		return map;
	}

	private static Object[] getFieldsValues(Object object, String... property) {
		Object[] fieldValues = new Object[property.length];
		for (int i = 0; i < property.length; i++) {
			fieldValues[i] = Reflections.getFieldValue(object, property[i]);
		}
		return fieldValues;
	}

	public static <E> Map<GroupKey, List<E>> duplicates(Collection<E> collection, String ... properties) {
		Map<GroupKey, List<E>> groups = group(collection, properties);

		// prepare keys to remove
		List<GroupKey> keysToRemove = new ArrayList<>();
		for (Entry<GroupKey, List<E>> entry : groups.entrySet()) {
			if (entry.getValue().size() == 1) {
				keysToRemove.add(entry.getKey());
			}
		}
		// remove unique values
		for (GroupKey key : keysToRemove) {
			groups.remove(key);
		}
		return groups;
	}

	@SuppressWarnings("unchecked")
	public static <E, T extends Collection<E>> T uniques(T collection, String ... properties) {
		Map<GroupKey, List<E>> groups = group(collection, properties);

		Collection<E> uniques = createNewInstanceOfCollection(collection.getClass());

		for (Entry<GroupKey, List<E>> entry : groups.entrySet()) {
			if (entry.getValue().size() == 1) {
				uniques.addAll(entry.getValue());
			}
		}
		return (T) uniques;
	}

	// List

	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<>();
	}

	@SafeVarargs
	public static <E> ArrayList<E> newArrayList(E... elements) {
		ArrayList<E> list = new ArrayList<>();
		java.util.Collections.addAll(list, elements);
		return list;
	}

	public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
		ArrayList<E> arrayList = new ArrayList<>();
		for (E e : elements) {
			arrayList.add(e);
		}
		return arrayList;
	}

	public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize) {
		return new ArrayList<>(initialArraySize);
	}

	public static <E> LinkedList<E> newLinkedList() {
		return new LinkedList<>();
	}

	@SafeVarargs
	public static <E> LinkedList<E> newLinkedList(E... elements) {
		LinkedList<E> list = new LinkedList<>();
		java.util.Collections.addAll(list, elements);
		return list;
	}

	public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
		LinkedList<E> linkedList = new LinkedList<>();
		for (E e : elements) {
			linkedList.add(e);
		}
		return linkedList;
	}

	// Sets

	public static <E> HashSet<E> newHashSet() {
		return new HashSet<>();
	}

	@SafeVarargs
	public static <E> HashSet<E> newHashSet(E... elements) {
		HashSet<E> set = newHashSet();
		java.util.Collections.addAll(set, elements);
		return set;
	}

	public static <E> HashSet<E> newHashSetWithInitialCapacity(int initialCapacity) {
		return new HashSet<>(initialCapacity);
	}

	public static <E> LinkedHashSet<E> newLinkedHashSet() {
		return new LinkedHashSet<>();
	}

	@SafeVarargs
	public static <E> LinkedHashSet<E> newLinkedHashSet(E... elements) {
		LinkedHashSet<E> set = newLinkedHashSet();
		java.util.Collections.addAll(set, elements);
		return set;
	}

	public static <E> LinkedHashSet<E> newLinkedHashSetWithInitialCapacity(int initialCapacity) {
		return new LinkedHashSet<>(initialCapacity);
	}

	@SafeVarargs
	public static <E extends Comparable<?>> TreeSet<E> newTreeSet(E... elements) {
		TreeSet<E> set = new TreeSet<>();
		java.util.Collections.addAll(set, elements);
		return set;
	}

	public static <E extends Comparable<?>> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
		TreeSet<E> set = newTreeSet();
		for (E e : elements) {
			set.add(e);
		}
		return set;
	}

	public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
		if (comparator == null) {
			throw new NullPointerException("Comparator cannot be null");
		}
		return new TreeSet<>(comparator);
	}
}