package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToLongArrayConverter implements Converter<List<Long>, Long[]> {

	@Override
	public Long[] convert(List<Long> source) {
		return source.toArray(new Long[] {});
	}
}