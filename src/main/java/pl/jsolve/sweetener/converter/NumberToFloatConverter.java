package pl.jsolve.sweetener.converter;

public class NumberToFloatConverter implements Converter<Number, Float> {

	@Override
	public Float convert(Number source) {
		return source.floatValue();
	}
}