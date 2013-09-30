package pl.jsolve.sweetener.converter;

import java.util.AbstractCollection;

public class CollectionToByteArrayConverter implements Converter<AbstractCollection<Byte>, Byte[]> {

	@Override
	public Byte[] convert(AbstractCollection<Byte> source) {
		return source.toArray(new Byte[] {});
	}
}