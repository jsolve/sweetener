package pl.jsolve.sweetener.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import pl.jsolve.sweetener.exception.DeepCopyException;

public class Objects {

	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}

	@SuppressWarnings("unchecked")
	public static <T> T deepCopy(T o) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, o);
			return (T) mapper.readValue(out.toByteArray(), o.getClass());
		} catch (IOException e) {
			throw new DeepCopyException(e.getMessage());
		}
	}

}
