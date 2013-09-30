package pl.jsolve.sweetener.converter;

public class NumberToLongConverter implements Converter<Number, Long> {

	@Override
	public Long convert(Number source) {
		return source.longValue();
	}
}