package pl.jsolve.sweetener.converter;

import java.util.TreeSet;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToTreeSetConverter implements Converter<Comparable<?>[], TreeSet<?>> {

	@Override
	public TreeSet<?> convert(Comparable<?>[] source) {
		return Collections.newTreeSet(source);
	}
}