package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String source) {
		return BigDecimal.valueOf(Double.valueOf(source));
	}
}