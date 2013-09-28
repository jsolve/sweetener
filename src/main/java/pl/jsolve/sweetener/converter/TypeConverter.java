package pl.jsolve.sweetener.converter;

import static java.util.Collections.synchronizedMap;
import static pl.jsolve.sweetener.core.Reflections.getClasses;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import pl.jsolve.sweetener.exception.ConversionException;

public final class TypeConverter {

	private static final Map<String, Converter<?, ?>> CONVERTERS = synchronizedMap(new HashMap<String, Converter<?, ?>>());

	private TypeConverter() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	static {
		registerConverter(new BooleanToIntegerConverter());
		registerConverter(new IntegerToBooleanConverter());
		registerConverter(new ObjectToStringConverter());
		registerConverter(new StringToBooleanConverter());
		registerConverter(new StringToNumberConverter());
		registerConverter(new StringToIntegerConverter());
		registerConverter(new StringToShortConverter());
		registerConverter(new StringToLongConverter());
		registerConverter(new StringToBigDecimalConverter());
		registerConverter(new StringToBigIntegerConverter());
		registerConverter(new StringToByteConverter());
		registerConverter(new StringToDoubleConverter());
		registerConverter(new StringToFloatConverter());
	}

	@SuppressWarnings("unchecked")
	private static <S, T> void registerConverter(Converter<S, T> converter) {
		Method convertMethod = converter.getClass().getMethods()[0];
		Class<S> sourceClass = (Class<S>) convertMethod.getParameterTypes()[0];
		Class<T> targetClass = (Class<T>) convertMethod.getReturnType();
		registerConverter(sourceClass, targetClass, converter);
	}

	public static <S, T> void registerConverter(Class<S> sourceClass, Class<T> targetClass, Converter<S, T> converter) {
		String converterId = createConverterId(sourceClass, targetClass);
		CONVERTERS.put(converterId, converter);
	}

	public static <S, T> void unregisterConverter(Class<S> sourceClass, Class<T> targetClass) {
		String converterId = createConverterId(sourceClass, targetClass);
		CONVERTERS.remove(converterId);
	}

	private static String createConverterId(Class<?> sourceClass, Class<?> targetClass) {
		return String.format("%s:to:%s", sourceClass.getName(), targetClass.getName());
	}

	public static <S, T> T convert(S source, Class<T> targetClass) {
		if(source == null) {
			return null;
		}
		if(isCastableToClass(source, targetClass)) {
			return targetClass.cast(source);
		}
		return tryToConvertUsingRegisteredConverters(source, targetClass);
	}

	private static boolean isCastableToClass(Object object, Class<?> clazz) {
		return clazz.isAssignableFrom(object.getClass());
	}

	private static <S, T> T tryToConvertUsingRegisteredConverters(S source, Class<T> targetClass) {
		Converter<S, T> converter = getSuitableConverter(source, targetClass);
		try {
			return targetClass.cast(converter.convert(source));
		} catch (Exception e) {
			throw new ConversionException(source.getClass(), targetClass, e);
		}
	}

	private static <S, T> Converter<S, T> getSuitableConverter(S source, Class<T> targetClass) {
		for (Class<?> sourceClazz : getClasses(source)) {
			for (Class<?> targetClazz : getClasses(targetClass)) {
				String converterId = createConverterId(sourceClazz, targetClazz);
				Converter<S, T> converter = (Converter<S, T>) CONVERTERS.get(converterId);
				if (converter != null) {
					return converter;
				}
			}
		}
		throw new ConversionException("No suitable converter was found. Use registerConverter() method to add your own converter",
				source.getClass(), targetClass);
	}
}