package pl.jsolve.sweetener.converter.numberto;


public class NumberToShortConverter extends NumberToAbstractConverter<Short> {

	@Override
	public Short convert(Number source) {
		return source.shortValue();
	}
}