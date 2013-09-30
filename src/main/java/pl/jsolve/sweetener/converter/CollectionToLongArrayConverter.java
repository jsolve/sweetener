package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToLongArrayConverter implements Converter<AbstractCollection<Long>, Long[]> {

	@Override
	public Long[] convert(AbstractCollection<Long> source) {
		return source.toArray(new Long[] {});
	}
}