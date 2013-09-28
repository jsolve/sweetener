package pl.jsolve.sweetener.converter;


public class StringToLongConverter implements Converter<String, Long> {

	@Override
	public Long convert(String source) {
		return Long.valueOf(source);
	}
}