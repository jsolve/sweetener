package pl.jsolve.sweetener.converter;

public class NumberToShortConverter implements Converter<Number, Short> {

	@Override
	public Short convert(Number source) {
		return source.shortValue();
	}
}