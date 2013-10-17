package pl.jsolve.sweetener.converter.stringTo;

import java.math.BigInteger;

public class StringToBigIntegerConverter extends StringToAbstractConverter<BigInteger> {

	@Override
	public BigInteger convert(String source) {
		return new BigInteger(source);
	}
}