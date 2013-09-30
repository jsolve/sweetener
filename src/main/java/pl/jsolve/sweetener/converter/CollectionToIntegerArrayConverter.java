package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToIntegerArrayConverter implements Converter<AbstractCollection<Integer>, Integer[]> {

	@Override
	public Integer[] convert(AbstractCollection<Integer> source) {
		return source.toArray(new Integer[] {});
	}
}