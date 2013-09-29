package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToShortArrayConverter implements Converter<List<Short>, Short[]> {

	@Override
	public Short[] convert(List<Short> source) {
		return source.toArray(new Short[] {});
	}
}