package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToCharacterArrayConverter implements Converter<List<Character>, Character[]> {

	@Override
	public Character[] convert(List<Character> source) {
		return source.toArray(new Character[] {});
	}
}