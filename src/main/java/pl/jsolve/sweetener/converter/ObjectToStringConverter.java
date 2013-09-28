package pl.jsolve.sweetener.converter;

public class ObjectToStringConverter implements Converter<Object, String> {

	@Override
	public String convert(Object source) {
		return source.toString();
	}
}