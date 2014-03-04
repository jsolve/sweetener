package pl.jsolve.sweetener.converter.numberto;


public class NumberToLongConverter extends NumberToAbstractConverter<Long> {

	@Override
	public Long convert(Number source) {
		return source.longValue();
	}
}