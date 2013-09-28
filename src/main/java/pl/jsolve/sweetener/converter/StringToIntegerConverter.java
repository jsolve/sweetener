package pl.jsolve.sweetener.converter;


public class StringToIntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		return Integer.parseInt(source);
	}
}