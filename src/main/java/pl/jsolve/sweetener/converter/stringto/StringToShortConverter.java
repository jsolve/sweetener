package pl.jsolve.sweetener.converter.stringto;

public class StringToShortConverter extends StringToAbstractConverter<Short> {

	@Override
	public Short convert(String source) {
		return Short.valueOf(source);
	}
}