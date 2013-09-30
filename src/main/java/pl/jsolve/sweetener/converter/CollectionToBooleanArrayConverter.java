package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToBooleanArrayConverter implements Converter<AbstractCollection<Boolean>, Boolean[]> {

	@Override
	public Boolean[] convert(AbstractCollection<Boolean> source) {
		return source.toArray(new Boolean[] {});
	}
}