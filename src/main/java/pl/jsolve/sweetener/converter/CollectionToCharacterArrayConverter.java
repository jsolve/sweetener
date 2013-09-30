package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToCharacterArrayConverter implements Converter<AbstractCollection<Character>, Character[]> {

	@Override
	public Character[] convert(AbstractCollection<Character> source) {
		return source.toArray(new Character[] {});
	}
}