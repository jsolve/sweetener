package pl.jsolve.sweetener.collection;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public final class Maps {

	private Maps() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

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
}