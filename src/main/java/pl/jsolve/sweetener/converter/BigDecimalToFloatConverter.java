package pl.jsolve.sweetener.converter;

import java.math.BigDecimal;

public class BigDecimalToFloatConverter implements Converter<BigDecimal, Float> {

	@Override
	public Float convert(BigDecimal source) {
		return source.floatValue();
	}
}