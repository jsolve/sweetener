package pl.jsolve.sweetener.converter;

public class StringToByteConverter implements Converter<String, Byte> {

	@Override
	public Byte convert(String source) {
		return Byte.valueOf(source);
	}
}