package pl.jsolve.sweetener.converter;

import java.util.List;

public class ListToByteArrayConverter implements Converter<List<Byte>, Byte[]> {

	@Override
	public Byte[] convert(List<Byte> source) {
		return source.toArray(new Byte[] {});
	}
}