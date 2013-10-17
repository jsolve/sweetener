package pl.jsolve.sweetener.converter;


public class StringToShortConverter implements Converter<String, Short> {

	@Override
	public Short convert(String source) {
		return Short.valueOf(source);
	}
}