package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;

public class CollectionToLongArrayConverter extends CollectionToAbstractConverter<Long[]> {

	@Override
	public Long[] convert(AbstractCollection<?> source) {
		return source.toArray(new Long[] {});
	}
}