package pl.jsolve.sweetener.converter;

public class NumberToIntegerConverter implements Converter<Number, Integer> {

	@Override
	public Integer convert(Number source) {
		return source.intValue();
	}
}