package pl.jsolve.sweetener.converter.numberTo;


public class NumberToLongConverter extends NumberToAbstractConverter<Long> {

	@Override
	public Long convert(Number source) {
		return source.longValue();
	}
}