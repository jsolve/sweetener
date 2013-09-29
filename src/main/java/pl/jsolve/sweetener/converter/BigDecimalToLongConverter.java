package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToLongConverter implements Converter<BigDecimal, Long> {

	@Override
	public Long convert(BigDecimal source) {
		return source.longValue();
	}
}