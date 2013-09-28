package pl.jsolve.sweetener.converter;

public class BooleanToIntegerConverter implements Converter<Boolean, Integer> {

	@Override
	public Integer convert(Boolean value) {
		return value.booleanValue() ? Integer.valueOf(1) : Integer.valueOf(0);
	}
}