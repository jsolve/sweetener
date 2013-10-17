package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;

public class CollectionToFloatArrayConverter extends CollectionToAbstractConverter<Float[]> {

	@Override
	public Float[] convert(AbstractCollection<?> source) {
		return source.toArray(new Float[] {});
	}
}