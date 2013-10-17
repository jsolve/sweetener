package pl.jsolve.sweetener.converter.arrayTo;

import java.util.Set;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToSetConverter extends ArrayToAbstractConverter<Set<?>> {

	@Override
	public Set<?> convert(Object[] source) {
		return Collections.newHashSet(source);
	}
}