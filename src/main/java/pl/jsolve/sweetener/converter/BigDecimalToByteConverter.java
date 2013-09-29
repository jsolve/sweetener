package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToByteConverter implements Converter<BigDecimal, Byte> {

	@Override
	public Byte convert(BigDecimal source) {
		return source.byteValue();
	}
}