package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToIntegerConverter implements Converter<BigDecimal, Integer> {

	@Override
	public Integer convert(BigDecimal source) {
		return source.intValue();
	}
}