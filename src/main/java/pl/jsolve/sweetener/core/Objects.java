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
		    }
		    o = getValueOfField(o, field);
		    levelOfNestedObject++;
		    if (o == null) {
			return null;
		    }
		}
	    }
	    clazz = clazz.getSuperclass();
	}
	throw new AccessToFieldException(String.format("The field %s does not exist",
		fieldsName[levelOfNestedObject]));
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
	    throw new AccessToFieldException(String.format("Exception during getting value of %s field",
		    field.getName()));
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
	Object firstFieldValue = getFieldValue(o1, path);
	Object secondFieldValue = getFieldValue(o2, path);
	return equals(firstFieldValue, secondFieldValue);
    }

    public static boolean equals(Object o1, String firstPath, Object o2, String secondPath) {
	Object firstFieldValue = getFieldValue(o1, firstPath);
	Object secondFieldValue = getFieldValue(o2, secondPath);
	return equals(firstFieldValue, secondFieldValue);
    }
}
