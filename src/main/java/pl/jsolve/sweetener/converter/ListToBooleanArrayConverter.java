package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToBooleanArrayConverter implements Converter<List<Boolean>, Boolean[]> {

	@Override
	public Boolean[] convert(List<Boolean> source) {
		return source.toArray(new Boolean[] {});
	}
}