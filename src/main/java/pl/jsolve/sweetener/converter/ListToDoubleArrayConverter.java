package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToDoubleArrayConverter implements Converter<List<Double>, Double[]> {

	@Override
	public Double[] convert(List<Double> source) {
		return source.toArray(new Double[] {});
	}
}