package pl.jsolve.sweetener.converter;

import java.util.LinkedList;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToLinkedListConverter implements Converter<Object[], LinkedList<?>> {

	@Override
	public LinkedList<?> convert(Object[] source) {
		return Collections.newLinkedList(source);
	}
}