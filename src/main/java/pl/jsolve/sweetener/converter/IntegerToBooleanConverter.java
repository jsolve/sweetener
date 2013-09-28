package pl.jsolve.sweetener.converter;

public class IntegerToBooleanConverter implements Converter<Integer, Boolean> {

	@Override
	public Boolean convert(Integer value) {
		return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
	}
}