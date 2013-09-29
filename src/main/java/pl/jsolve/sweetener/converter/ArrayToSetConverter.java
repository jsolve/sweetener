package pl.jsolve.sweetener.converter;

import java.util.Set;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToSetConverter implements Converter<Object[], Set<?>> {

	@Override
	public Set<?> convert(Object[] source) {
		return Collections.newHashSet(source);
	}
}