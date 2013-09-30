package pl.jsolve.sweetener.converter;

public class NumberToDoubleConverter implements Converter<Number, Double> {

	@Override
	public Double convert(Number source) {
		return source.doubleValue();
	}
}