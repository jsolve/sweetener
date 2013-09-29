package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToDoubleConverter implements Converter<BigDecimal, Double> {

	@Override
	public Double convert(BigDecimal source) {
		return source.doubleValue();
	}
}