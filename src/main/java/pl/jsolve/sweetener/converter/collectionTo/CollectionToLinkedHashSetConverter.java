package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;
import java.util.LinkedHashSet;

public class CollectionToLinkedHashSetConverter extends CollectionToAbstractConverter<LinkedHashSet<?>> {

	@Override
	public LinkedHashSet<?> convert(AbstractCollection<?> source) {
		return new LinkedHashSet<>(source);
	}
}