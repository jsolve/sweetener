package pl.jsolve.sweetener.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import org.codehaus.jackson.map.ObjectMapper;

import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.exception.DeepCopyException;

public class Objects {

    private final static String DOT = "\\.";
    private final static String EMPTY_STRING = "";
    private static ObjectMapper mapper;

    private Objects() {
	throw new AssertionError("Using constructor of this class is prohibited.");
    }

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

    
    public static String nullSafeToString(Object nullableObject) {
	return (nullableObject == null) ? EMPTY_STRING : nullableObject.toString();
    }

    public static String nullSafeString(String nullableString) {
	return (nullableString == null) ? EMPTY_STRING : nullableString;
    }

    public static Integer nullSafeInteger(Integer nullableInteger) {
	return (nullableInteger == null) ? 0 : nullableInteger;
    }

    public static Long nullSafeLong(Long nullableInteger) {
	return (nullableInteger == null) ? 0L : nullableInteger;
    }

    public static Double nullSafeDouble(Double nullableDouble) {
	return (nullableDouble == null) ? 0.0 : nullableDouble;
    }

    public static Float nullSafeFloat(Float nullableFloat) {
	return (nullableFloat == null) ? 0.0f : nullableFloat;
    }

    public static <T> T nullSafe(T nullableObject, OnNullBehavior<T> onNullObjectBevior) {
	return (nullableObject == null) ? onNullObjectBevior.onNull() : nullableObject;
    }

    public static boolean equals(Object o1, Object o2) {
	if (o1 == null && o2 == null) {
	    return true;
	}
	if ((o1 == null && o2 != null) || (o1 != null && o2 == null)) {
	    return false;
	}
	return o1.equals(o2);
    }

    public static boolean equals(Object o1, Object o2, String path) {
	Object firstFieldValue = Reflections.getFieldValue(o1, path);
	Object secondFieldValue = Reflections.getFieldValue(o2, path);
	return equals(firstFieldValue, secondFieldValue);
    }

    public static boolean equals(Object o1, String firstPath, Object o2, String secondPath) {
	Object firstFieldValue = Reflections.getFieldValue(o1, firstPath);
	Object secondFieldValue = Reflections.getFieldValue(o2, secondPath);
	return equals(firstFieldValue, secondFieldValue);
    }
}
