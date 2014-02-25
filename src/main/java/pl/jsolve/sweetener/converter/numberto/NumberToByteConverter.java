package pl.jsolve.sweetener.converter.numberto;


public class NumberToByteConverter extends NumberToAbstractConverter<Byte> {

	@Override
	public Byte convert(Number source) {
		return source.byteValue();
	}
}