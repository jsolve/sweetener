package pl.jsolve.sweetener.converter;

public class NumberToBooleanConverter implements Converter<Number, Boolean> {

	@Override
	public Boolean convert(Number source) {
		return source.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
	}
}