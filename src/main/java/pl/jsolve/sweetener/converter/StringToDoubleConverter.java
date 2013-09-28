package pl.jsolve.sweetener.converter;

public class StringToDoubleConverter implements Converter<String, Double> {

	@Override
	public Double convert(String source) {
		return Double.valueOf(source);
	}
}