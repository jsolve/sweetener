package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;

public class CollectionToBooleanArrayConverter extends CollectionToAbstractConverter<Boolean[]> {

	@Override
	public Boolean[] convert(AbstractCollection<?> source) {
		return source.toArray(new Boolean[] {});
	}
}