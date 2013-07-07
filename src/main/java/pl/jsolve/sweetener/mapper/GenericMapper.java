package pl.jsolve.sweetener.mapper;

import static pl.jsolve.sweetener.core.Reflections.getFieldsAnnotatedBy;

import java.lang.reflect.Field;
import java.util.List;

public final class GenericMapper {

	public static <T, V> V map(T object, Class<V> targetClass) throws Exception {
		throwExceptionWhenIsNotMappableToTargetClass(object, targetClass);
		List<Field> fieldsAnnotatedByMapExactlyTo = getFieldsAnnotatedBy(object, MapExactlyTo.class);
		V target = targetClass.newInstance();
		for (Field field : fieldsAnnotatedByMapExactlyTo) {
			String targetFieldName = getMapExactlyToValue(field);
			field.setAccessible(true);
			Field targetField = targetClass.getDeclaredField(targetFieldName);
			targetField.setAccessible(true);
			targetField.set(target, field.get(object));
			targetField.setAccessible(false);
			field.setAccessible(false);
		}
		return target;
	}

	private static String getMapExactlyToValue(Field field) {
		return field.getAnnotation(MapExactlyTo.class).value();
	}

	private static <T, V> void throwExceptionWhenIsNotMappableToTargetClass(T object, Class<V> targetClass) {
		MappableTo mappableTo = object.getClass().getAnnotation(MappableTo.class);
		if (mappableTo == null || mappableTo.value() != targetClass) {
			throw new RuntimeException(object.getClass() + " is not mappable to " + targetClass);
		}
	}
}