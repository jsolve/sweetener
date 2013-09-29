package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToBooleanConverter implements Converter<BigDecimal, Boolean> {

	@Override
	public Boolean convert(BigDecimal source) {
		return source.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
	}
}