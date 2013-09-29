package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToObjectArrayConverter implements Converter<List<Object>, Object> {

	@Override
	public Object convert(List<Object> source) {
		return source.toArray();
	}
}