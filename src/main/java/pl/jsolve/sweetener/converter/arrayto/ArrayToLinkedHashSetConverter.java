package pl.jsolve.sweetener.converter.arrayto;

import java.util.LinkedHashSet;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToLinkedHashSetConverter extends ArrayToAbstractConverter<LinkedHashSet<?>> {

	@Override
	public LinkedHashSet<?> convert(Object[] source) {
		return Collections.newLinkedHashSet(source);
	}
}