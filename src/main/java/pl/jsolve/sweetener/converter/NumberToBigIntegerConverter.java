package pl.jsolve.sweetener.converter;

import java.math.BigInteger;

public class NumberToBigIntegerConverter implements Converter<Number, BigInteger> {

	@Override
	public BigInteger convert(Number source) {
		return BigInteger.valueOf(source.intValue());
	}
}