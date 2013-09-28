package pl.jsolve.sweetener.converter;

public class StringToBooleanConverter implements Converter<String, Boolean> {

	@Override
	public Boolean convert(String source) {
		return "TRUE".equals(source.trim().toUpperCase());
	}
}