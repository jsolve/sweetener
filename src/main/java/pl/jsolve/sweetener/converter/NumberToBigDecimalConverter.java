package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class NumberToBigDecimalConverter implements Converter<Number, BigDecimal> {

	@Override
	public BigDecimal convert(Number source) {
		return new BigDecimal(source.toString());
	}
}