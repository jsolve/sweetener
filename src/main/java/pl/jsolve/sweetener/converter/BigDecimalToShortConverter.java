package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToShortConverter implements Converter<BigDecimal, Short> {

	@Override
	public Short convert(BigDecimal source) {
		return source.shortValue();
	}
}