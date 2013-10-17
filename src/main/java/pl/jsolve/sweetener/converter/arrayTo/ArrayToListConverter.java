package pl.jsolve.sweetener.converter.arrayTo;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;

public class ArrayToListConverter extends ArrayToAbstractConverter<List<?>> {

	@Override
	public List<?> convert(Object[] source) {
		return Collections.newArrayList(source);
	}
}