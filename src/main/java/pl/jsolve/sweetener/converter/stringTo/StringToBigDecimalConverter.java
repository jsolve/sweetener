package pl.jsolve.sweetener.converter.stringTo;

import java.math.BigDecimal;

public class StringToBigDecimalConverter extends StringToAbstractConverter<BigDecimal> {

	@Override
	public BigDecimal convert(String source) {
		return BigDecimal.valueOf(Double.valueOf(source));
	}
}