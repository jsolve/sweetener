package pl.jsolve.sweetener.converter.stringTo;

public class StringToIntegerConverter extends StringToAbstractConverter<Integer> {

	@Override
	public Integer convert(String source) {
		return Integer.parseInt(source);
	}
}