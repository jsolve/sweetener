package pl.jsolve.sweetener.converter.numberto;

import java.math.BigDecimal;

public class NumberToBigDecimalConverter extends NumberToAbstractConverter<BigDecimal> {

	@Override
	public BigDecimal convert(Number source) {
		return new BigDecimal(source.toString());
	}
}