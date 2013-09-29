package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalToBigIntegerConverter implements Converter<BigDecimal, BigInteger> {

	@Override
	public BigInteger convert(BigDecimal source) {
		return source.toBigInteger();
	}
}