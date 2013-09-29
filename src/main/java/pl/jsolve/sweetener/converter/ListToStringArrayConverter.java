package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToStringArrayConverter implements Converter<List<String>, String[]> {

	@Override
	public String[] convert(List<String> source) {
		return source.toArray(new String[] {});
	}
}