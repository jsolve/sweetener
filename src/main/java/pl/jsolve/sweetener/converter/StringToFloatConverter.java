package pl.jsolve.sweetener.converter;

public class StringToFloatConverter implements Converter<String, Float> {

	@Override
	public Float convert(String source) {
		return Float.valueOf(source);
	}
}