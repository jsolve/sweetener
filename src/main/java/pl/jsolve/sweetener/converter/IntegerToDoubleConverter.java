package pl.jsolve.sweetener.converter;

public class IntegerToDoubleConverter implements Converter<Integer, Double> {

	@Override
	public Double convert(Integer source) {
		return source.doubleValue();
	}
}