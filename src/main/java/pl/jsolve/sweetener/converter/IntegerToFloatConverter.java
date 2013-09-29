package pl.jsolve.sweetener.converter;

public class IntegerToFloatConverter implements Converter<Integer, Float> {
	@Override
	public Float convert(Integer source) {
		return source.floatValue();
	}
}