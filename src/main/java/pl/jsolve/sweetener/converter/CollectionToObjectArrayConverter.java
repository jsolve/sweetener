package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToObjectArrayConverter implements Converter<AbstractCollection<?>, Object> {

	@Override
	public Object convert(AbstractCollection<?> source) {
		return source.toArray();
	}
}