package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

class MapExactlyToMapping implements AnnotationMapping {

	private static final Class<MapExactlyTo> MAP_EXACTLY_TO_ANNOTATION_CLASS = MapExactlyTo.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {

		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_EXACTLY_TO_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			String targetFieldName = field.getAnnotation(MAP_EXACTLY_TO_ANNOTATION_CLASS).value();
			throwExceptionWhenTargetFieldIsNotPresent(targetObject, targetFieldName);
			String sourceObjectFieldName = field.getName();
			Object sourceObjectFieldValue = Reflections.getFieldValue(sourceObject, sourceObjectFieldName);
			Reflections.setFieldValue(targetObject, targetFieldName, sourceObjectFieldValue);
		}
	}

	private static void throwExceptionWhenTargetFieldIsNotPresent(Object targetObject, String targetFieldName) {
		try {
			Reflections.getFieldValue(targetObject, targetFieldName);
		} catch (AccessToFieldException e) {
			throw new MappingException("Class %s does not contain field %s", targetObject.getClass(), targetFieldName);
		}
	}
}