package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToStringArrayConverter implements Converter<AbstractCollection<String>, String[]> {

	@Override
	public String[] convert(AbstractCollection<String> source) {
		return source.toArray(new String[] {});
	}
}