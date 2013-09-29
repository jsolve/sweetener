package pl.jsolve.sweetener.converter;

public class IntegerToShortConverter implements Converter<Integer, Short> {

	@Override
	public Short convert(Integer source) {
		return source.shortValue();
	}
}