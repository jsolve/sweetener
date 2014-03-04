package pl.jsolve.sweetener.converter.numberto;


public class NumberToIntegerConverter extends NumberToAbstractConverter<Integer> {

	@Override
	public Integer convert(Number source) {
		return source.intValue();
	}
}