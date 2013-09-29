package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class IntegerToBigDecimalConverter implements Converter<Integer, BigDecimal> {

	@Override
	public BigDecimal convert(Integer source) {
		return new BigDecimal(source);
	}
}