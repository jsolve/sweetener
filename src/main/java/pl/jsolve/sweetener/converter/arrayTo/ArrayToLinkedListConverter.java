package pl.jsolve.sweetener.converter.arrayTo;

import java.util.LinkedList;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToLinkedListConverter extends ArrayToAbstractConverter<LinkedList<?>> {

	@Override
	public LinkedList<?> convert(Object[] source) {
		return Collections.newLinkedList(source);
	}
}