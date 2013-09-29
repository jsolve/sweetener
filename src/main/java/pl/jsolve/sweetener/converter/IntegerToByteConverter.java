package pl.jsolve.sweetener.converter;

public class IntegerToByteConverter implements Converter<Integer, Byte> {

	@Override
	public Byte convert(Integer source) {
		return source.byteValue();
	}
}