package pl.jsolve.sweetener.converter;

import java.math.BigInteger;

public class IntegerToBigIntegerConverter implements Converter<Integer, BigInteger> {

	@Override
	public BigInteger convert(Integer source) {
		return BigInteger.valueOf(source.intValue());
	}
}