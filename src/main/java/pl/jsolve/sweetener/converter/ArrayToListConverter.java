package pl.jsolve.sweetener.converter;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToListConverter implements Converter<Object[], List<?>> {

	@Override
	public List<?> convert(Object[] source) {
		return Collections.newArrayList(source);
	}
}