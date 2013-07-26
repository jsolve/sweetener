package pl.jsolve.sweetener.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

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
		int to = getTo(resultsPerPage, totalElements, from);
		Collection<T> elementsOfPage = truncate(collection, from, to);
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

	public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
		LinkedList<E> linkedList = new LinkedList<>();
		for (E e : elements) {
			linkedList.add(e);
		}
		return linkedList;
	}

	// Map

	public static <K, V> ConcurrentHashMap<K, V> newConcurrentMap() {
		return new ConcurrentHashMap<>();
	}

	public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> type) {
		if (type == null) {
			throw new NullPointerException("Type cannot be null");
		}
		return new EnumMap<>(type);
	}

	public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
		return new EnumMap<>(map);
	}

	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<>();
	}

	public static <K, V> HashMap<K, V> newHashMapWithInitialCapacity(int initialCapacity) {
		return new HashMap<>(initialCapacity);
	}

	public static <K, V> HashMap<K, V> newHashMap(
			Map<? extends K, ? extends V> map) {
		return new HashMap<>(map);
	}

	public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
		return new IdentityHashMap<>();
	}

	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
		return new LinkedHashMap<>();
	}

	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
		return new LinkedHashMap<>(map);
	}

	public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMap() {
		return new TreeMap<>();
	}

	public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
		return new TreeMap<>(map);
	}

	public static <C, K extends C, V> TreeMap<K, V> newTreeMap(Comparator<C> comparator) {
		return new TreeMap<>(comparator);
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

	public static <E> LinkedHashSet<E> newLinkedHashSetWithInitialCapacity(int initialCapacity) {
		return new LinkedHashSet<>(initialCapacity);
	}

	public static <E extends Comparable<?>> TreeSet<E> newTreeSet() {
		return new TreeSet<>();
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