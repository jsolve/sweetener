package pl.jsolve.sweetener.converter.stringTo;

public class StringToByteConverter extends StringToAbstractConverter<Byte> {

	@Override
	public Byte convert(String source) {
		return Byte.valueOf(source);
	}
}