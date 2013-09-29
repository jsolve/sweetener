package pl.jsolve.sweetener.converter;

public class IntegerToLongConverter implements Converter<Integer, Long> {
	@Override
	public Long convert(Integer source) {
		return source.longValue();
	}
}