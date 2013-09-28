package pl.jsolve.sweetener.converter;

import java.math.BigInteger;

public class StringToBigIntegerConverter implements Converter<String, BigInteger> {

	@Override
	public BigInteger convert(String source) {
		return new BigInteger(source);
	}
}