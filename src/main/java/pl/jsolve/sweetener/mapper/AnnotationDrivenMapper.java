package pl.jsolve.sweetener.mapper;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.exception.MappingException;

public final class AnnotationDrivenMapper {

	public static <T, V> boolean isMappableToTargetClass(T object, Class<V> targetClass) {
		MappableTo mappableTo = object.getClass().getAnnotation(MappableTo.class);
		return mappableTo != null && mappableTo.value() == targetClass;
	}

	public static <T, V> V map(T sourceObject, Class<V> targetClass) {
		throwExceptionWhenIsNotMappableToTargetClass(sourceObject, targetClass);
		List<Field> fieldsAnnotatedByMapExactlyTo = Reflections.getFieldsAnnotatedBy(sourceObject, MapExactlyTo.class);
		V targetObject = Reflections.tryToCreateNewInstance(targetClass);
		for (Field field : fieldsAnnotatedByMapExactlyTo) {
			String targetFieldName = getMapExactlyToAnnotationValue(field);
			Field targetField = Reflections.onObject(targetObject).getDeclaredField(targetFieldName);
			throwExceptionWhenTargetFieldIsNull(targetClass, targetField, targetFieldName);
			Object sourceObjectFieldValue = Reflections.onObject(sourceObject).getFieldValue(field);
			Reflections.onObject(targetObject).setField(targetField, sourceObjectFieldValue);
		}
		return targetObject;
	}

	private static <T, V> void throwExceptionWhenIsNotMappableToTargetClass(T object, Class<V> targetClass) {
		if (!isMappableToTargetClass(object, targetClass)) {
			throw new MappingException("%s is not mappable to %s", object.getClass(), targetClass);
		}
	}

	private static String getMapExactlyToAnnotationValue(Field field) {
		return field.getAnnotation(MapExactlyTo.class).value();
	}

	private static void throwExceptionWhenTargetFieldIsNull(Class<?> targetClass, Field targetField, String targetFieldName) {
		if (targetField == null) {
			throw new MappingException("Class %s does not contain field %s", targetClass, targetFieldName);
		}
	}
}