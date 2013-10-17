package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;

public class CollectionToByteArrayConverter extends CollectionToAbstractConverter<Byte[]> {

	@Override
	public Byte[] convert(AbstractCollection<?> source) {
		return source.toArray(new Byte[] {});
	}
}