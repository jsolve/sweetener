package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToFloatArrayConverter implements Converter<AbstractCollection<Float>, Float[]> {

	@Override
	public Float[] convert(AbstractCollection<Float> source) {
		return source.toArray(new Float[] {});
	}
}