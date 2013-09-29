package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToFloatArrayConverter implements Converter<List<Float>, Float[]> {

	@Override
	public Float[] convert(List<Float> source) {
		return source.toArray(new Float[] {});
	}
}