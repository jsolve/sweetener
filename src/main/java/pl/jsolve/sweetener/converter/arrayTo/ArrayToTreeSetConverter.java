package pl.jsolve.sweetener.converter.arrayTo;

import java.util.TreeSet;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.converter.Converter;

public class ArrayToTreeSetConverter implements Converter<Comparable<?>[], TreeSet<?>> {

	@Override
	public TreeSet<?> convert(Comparable<?>[] source) {
		return Collections.newTreeSet(source);
	}
}