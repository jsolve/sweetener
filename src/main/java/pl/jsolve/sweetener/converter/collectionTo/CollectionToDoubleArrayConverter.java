package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;

public class CollectionToDoubleArrayConverter extends CollectionToAbstractConverter<Double[]> {

	@Override
	public Double[] convert(AbstractCollection<?> source) {
		return source.toArray(new Double[] {});
	}
}