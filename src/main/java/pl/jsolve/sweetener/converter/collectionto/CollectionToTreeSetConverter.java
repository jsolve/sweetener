package pl.jsolve.sweetener.converter.collectionto;

import java.util.AbstractCollection;
import java.util.TreeSet;

public class CollectionToTreeSetConverter extends CollectionToAbstractConverter<TreeSet<?>> {

	@Override
	public TreeSet<?> convert(AbstractCollection<?> source) {
		return new TreeSet<>(source);
	}
}