package pl.jsolve.sweetener.converter;

import java.util.LinkedHashSet;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToLinkedHashSetConverter implements Converter<Object[], LinkedHashSet<?>> {

	@Override
	public LinkedHashSet<?> convert(Object[] source) {
		return Collections.newLinkedHashSet(source);
	}
}