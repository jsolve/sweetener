package pl.jsolve.sweetener.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import org.codehaus.jackson.map.ObjectMapper;

import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.exception.DeepCopyException;

public class Objects {

    private final static String DOT = "\\.";
    private static ObjectMapper mapper;

    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T o) {
	try {
	    initialObjectMapper();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    mapper.writeValue(out, o);
	    return (T) mapper.readValue(out.toByteArray(), o.getClass());
	} catch (IOException e) {
	    throw new DeepCopyException(e.getMessage());
	}
    }

    public static void initialObjectMapper() {
	if (mapper == null) {
	    mapper = new ObjectMapper();
	}
    }

    public static Object getFieldValue(Object o, String stringOfFieldsName) {

	String[] fieldsName = stringOfFieldsName.split(DOT);
	int levelOfNestedObject = 0;
	Class<?> clazz = o.getClass();
	while (!Object.class.equals(clazz)) {

	    for (int i = levelOfNestedObject; i < fieldsName.length; i++) {
		Field field = getDeclaredField(fieldsName[i], o.getClass());
		if (field != null) {
		    boolean isLastNestedObject = (i == fieldsName.length - 1);
		    if (isLastNestedObject) {
			return getValueOfField(o, field);
		    } else {
			o = getValueOfField(o, field);
			levelOfNestedObject++;
			if (o == null) {
			    return null;
			}
		    }
		}
	    }
	    clazz = clazz.getSuperclass();
	}
	throw new AccessToFieldException(String.format("The field %s does not exist", fieldsName[levelOfNestedObject]));
    }

    private static Field getDeclaredField(String fieldName, Class<?> clazz) {
	try {
	    return clazz.getDeclaredField(fieldName);
	} catch (Exception ex) {
	    return null;
	}
    }

    private static Object getValueOfField(Object o, Field field) {
	try {
	    field.setAccessible(true);
	    return field.get(o);
	} catch (Exception e) {
	    throw new AccessToFieldException(String.format("Exception during getting value of %s field", field.getName()));
	}
    }

}
