package pl.jsolve.sweetener.converter;

import java.lang.reflect.Method;

import pl.jsolve.sweetener.converter.arrayto.ArrayToLinkedHashSetConverter;
import pl.jsolve.sweetener.converter.arrayto.ArrayToLinkedListConverter;
import pl.jsolve.sweetener.converter.arrayto.ArrayToListConverter;
import pl.jsolve.sweetener.converter.arrayto.ArrayToSetConverter;
import pl.jsolve.sweetener.converter.arrayto.ArrayToTreeSetConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToBooleanArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToByteArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToCharacterArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToDoubleArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToFloatArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToIntegerArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToLinkedHashSetConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToLinkedListConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToListConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToLongArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToObjectArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToSetConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToShortArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToStringArrayConverter;
import pl.jsolve.sweetener.converter.collectionto.CollectionToTreeSetConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToBigDecimalConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToBigIntegerConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToBooleanConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToByteConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToDoubleConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToFloatConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToIntegerConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToLongConverter;
import pl.jsolve.sweetener.converter.numberto.NumberToShortConverter;
import pl.jsolve.sweetener.converter.stringto.StringToBigDecimalConverter;
import pl.jsolve.sweetener.converter.stringto.StringToBigIntegerConverter;
import pl.jsolve.sweetener.converter.stringto.StringToBooleanConverter;
import pl.jsolve.sweetener.converter.stringto.StringToByteConverter;
import pl.jsolve.sweetener.converter.stringto.StringToDoubleConverter;
import pl.jsolve.sweetener.converter.stringto.StringToFloatConverter;
import pl.jsolve.sweetener.converter.stringto.StringToIntegerConverter;
import pl.jsolve.sweetener.converter.stringto.StringToLongConverter;
import pl.jsolve.sweetener.converter.stringto.StringToNumberConverter;
import pl.jsolve.sweetener.converter.stringto.StringToShortConverter;
import pl.jsolve.sweetener.exception.ConversionException;

public final class TypeConverter {

	private static final PrimitiveClassToNullableClassConverter PRIMITIVES_CONVERTER = new PrimitiveClassToNullableClassConverter();
	private static final ConvertersContainer CONVERTERS_CONTAINER = new ConvertersContainer();

	private TypeConverter() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	static {
		registerConverter(new BooleanToIntegerConverter());
		registerConverter(new ObjectToStringConverter());

		// string to numbers
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

		// number to numbers
		registerConverter(new NumberToBooleanConverter());
		registerConverter(new NumberToIntegerConverter());
		registerConverter(new NumberToShortConverter());
		registerConverter(new NumberToLongConverter());
		registerConverter(new NumberToBigDecimalConverter());
		registerConverter(new NumberToBigIntegerConverter());
		registerConverter(new NumberToByteConverter());
		registerConverter(new NumberToDoubleConverter());
		registerConverter(new NumberToFloatConverter());

		// array to collections
		registerConverter(new ArrayToSetConverter());
		registerConverter(new ArrayToTreeSetConverter());
		registerConverter(new ArrayToLinkedHashSetConverter());
		registerConverter(new ArrayToListConverter());
		registerConverter(new ArrayToLinkedListConverter());

		// collection to arrays
		registerConverter(new CollectionToStringArrayConverter());
		registerConverter(new CollectionToBooleanArrayConverter());
		registerConverter(new CollectionToByteArrayConverter());
		registerConverter(new CollectionToCharacterArrayConverter());
		registerConverter(new CollectionToDoubleArrayConverter());
		registerConverter(new CollectionToFloatArrayConverter());
		registerConverter(new CollectionToIntegerArrayConverter());
		registerConverter(new CollectionToLongArrayConverter());
		registerConverter(new CollectionToShortArrayConverter());
		registerConverter(new CollectionToObjectArrayConverter());

		// collection to collections
		registerConverter(new CollectionToSetConverter());
		registerConverter(new CollectionToLinkedHashSetConverter());
		registerConverter(new CollectionToTreeSetConverter());
		registerConverter(new CollectionToListConverter());
		registerConverter(new CollectionToLinkedListConverter());

		// date
		registerConverter(new LongToDateConverter());
		registerConverter(new DateToLongConverter());
		registerConverter(new LongToCalendarConverter());
		registerConverter(new CalendarToLongConverter());
		registerConverter(new DateToCalendarConverter());
		registerConverter(new CalendarToDateConverter());
	}

	@SuppressWarnings("unchecked")
	private static <S, T> void registerConverter(Converter<S, T> converter) {
		Method convertMethod = converter.getClass().getMethods()[0];
		Class<S> sourceClass = (Class<S>) convertMethod.getParameterTypes()[0];
		Class<T> targetClass = (Class<T>) convertMethod.getReturnType();
		CONVERTERS_CONTAINER.registerConverter(sourceClass, targetClass, converter);
	}

	public static <S, T> T convert(S source, Class<T> targetClass) {
		if (source == null || targetClass == null) {
			return null;
		}
		if (targetClass.isPrimitive()) {
			targetClass = (Class<T>) PRIMITIVES_CONVERTER.convert(targetClass);
		}
		if (isCastableToClass(source, targetClass)) {
			return targetClass.cast(source);
		}
		return tryToConvertUsingRegisteredConverters(source, targetClass);
	}

	private static boolean isCastableToClass(Object object, Class<?> clazz) {
		return clazz.isAssignableFrom(object.getClass());
	}

	private static <S, T> T tryToConvertUsingRegisteredConverters(S source, Class<T> targetClass) {
		Converter<S, T> converter = CONVERTERS_CONTAINER.getSuitableConverter(source, targetClass);
		throwExceptionWhenNoSuitableConverterWasFound(source, targetClass, converter);
		try {
			return targetClass.cast(converter.convert(source));
		} catch (Exception e) {
			throw new ConversionException(source.getClass(), targetClass, e);
		}
	}

	private static <S, T> void throwExceptionWhenNoSuitableConverterWasFound(S source, Class<T> targetClass, Converter<S, T> converter) {
		if (converter == null) {
			throw new ConversionException("No suitable converter was found. Use registerConverter() method to add your own converter",
					source.getClass(), targetClass);
		}
	}

	public static <S, T> void registerConverter(Class<S> sourceClass, Class<T> targetClass, Converter<S, T> converter) {
		CONVERTERS_CONTAINER.registerConverter(sourceClass, targetClass, converter);
	}

	public static <S, T> void unregisterConverter(Class<S> sourceClass, Class<T> targetClass) {
		CONVERTERS_CONTAINER.unregisterConverter(sourceClass, targetClass);
	}
}