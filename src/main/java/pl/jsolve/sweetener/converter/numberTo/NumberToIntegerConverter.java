package pl.jsolve.sweetener.converter.numberTo;


public class NumberToIntegerConverter extends NumberToAbstractConverter<Integer> {

	@Override
	public Integer convert(Number source) {
		return source.intValue();
	}
}