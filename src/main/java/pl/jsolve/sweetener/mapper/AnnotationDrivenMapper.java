package pl.jsolve.sweetener.mapper;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.exception.MappingException;

public final class AnnotationDrivenMapper {

	private AnnotationDrivenMapper() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	public static <T, V> V map(T sourceObject, Class<V> targetClass) {
		throwExceptionWhenIsNotMappableToTargetClass(sourceObject, targetClass);
		List<Field> fieldsAnnotatedByMapExactlyTo = Reflections.getFieldsAnnotatedBy(sourceObject, MapExactlyTo.class);
		V targetObject = Reflections.tryToCreateNewInstance(targetClass);
		for (Field annotatedField : fieldsAnnotatedByMapExactlyTo) {
			String targetFieldName = getMapExactlyToAnnotationValue(annotatedField);
			throwExceptionWhenTargetFieldIsNotPresent(targetClass, targetFieldName);
			Object sourceObjectFieldValue = Reflections.getFieldValue(sourceObject, annotatedField.getName());
			Reflections.setFieldValue(targetObject, targetFieldName, sourceObjectFieldValue);
		}
		return targetObject;
	}

	private static <T, V> void throwExceptionWhenIsNotMappableToTargetClass(T object, Class<V> targetClass) {
		if (!isMappableToTargetClass(object, targetClass)) {
			throw new MappingException("%s is not mappable to %s", object.getClass(), targetClass);
		}
	}

	public static <T, V> boolean isMappableToTargetClass(T object, Class<V> targetClass) {
		MappableTo mappableTo = object.getClass().getAnnotation(MappableTo.class);
		return mappableTo != null && mappableTo.value() == targetClass;
	}

	private static String getMapExactlyToAnnotationValue(Field field) {
		return field.getAnnotation(MapExactlyTo.class).value();
	}

	private static void throwExceptionWhenTargetFieldIsNotPresent(Class<?> targetClass, String targetFieldName) {
		try {
			targetClass.getDeclaredField(targetFieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new MappingException("Class %s does not contain field %s", targetClass, targetFieldName);
		}
	}
}