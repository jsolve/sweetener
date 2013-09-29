package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToIntegerArrayConverter implements Converter<List<Integer>, Integer[]> {

	@Override
	public Integer[] convert(List<Integer> source) {
		return source.toArray(new Integer[] {});
	}
}