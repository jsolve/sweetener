package pl.jsolve.sweetener.converter.stringTo;

public class StringToLongConverter extends StringToAbstractConverter<Long> {

	@Override
	public Long convert(String source) {
		return Long.valueOf(source);
	}
}