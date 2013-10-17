package pl.jsolve.sweetener.converter.collectionTo;

import java.util.AbstractCollection;

public class CollectionToCharacterArrayConverter extends CollectionToAbstractConverter<Character[]> {

	@Override
	public Character[] convert(AbstractCollection<?> source) {
		return source.toArray(new Character[] {});
	}
}