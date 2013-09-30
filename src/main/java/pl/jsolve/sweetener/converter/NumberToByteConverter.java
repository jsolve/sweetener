package pl.jsolve.sweetener.converter;

public class NumberToByteConverter implements Converter<Number, Byte> {

	@Override
	public Byte convert(Number source) {
		return source.byteValue();
	}
}