package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToShortArrayConverter implements Converter<AbstractCollection<Short>, Short[]> {

	@Override
	public Short[] convert(AbstractCollection<Short> source) {
		return source.toArray(new Short[] {});
	}
}