package pl.jsolve.sweetener.converter.numberTo;

import java.math.BigDecimal;

public class NumberToBigDecimalConverter extends NumberToAbstractConverter<BigDecimal> {

	@Override
	public BigDecimal convert(Number source) {
		return new BigDecimal(source.toString());
	}
}