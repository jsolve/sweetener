package pl.jsolve.sweetener.nullsafe;

public final class NullSafeUtils {

    private static final String EMPTY_STRING = "";

    private NullSafeUtils() {
	throw new AssertionError("Using constructor of this class is prohibited.");
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

    public static <T> T nullSafe(T nullableObject, OnNullBehavior<T> onNullObjectBevior) {
	return (nullableObject == null) ? onNullObjectBevior.onNull() : nullableObject;
    }
}