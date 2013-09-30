package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToDoubleArrayConverter implements Converter<AbstractCollection<Double>, Double[]> {

	@Override
	public Double[] convert(AbstractCollection<Double> source) {
		return source.toArray(new Double[] {});
	}
}