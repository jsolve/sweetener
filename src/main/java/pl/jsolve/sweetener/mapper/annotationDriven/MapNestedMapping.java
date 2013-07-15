package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

class MapNestedMapping implements AnnotationMapping {

	private static final String DOT = ".";
	private static final Class<MapNested> MAP_NESTED_ANNOTATION_CLASS = MapNested.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {

		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_NESTED_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapNested mapNestedAnnotation = field.getAnnotation(MAP_NESTED_ANNOTATION_CLASS);
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapNestedAnnotation);
		}
	}

	public final <S, T> void applyOnFieldWithAnnotation(S sourceObject, T targetObject, Field field, MapNested mapNestedAnnotation) {
		String targetFieldName = mapNestedAnnotation.to();
		throwExceptionWhenTargetFieldIsNotPresent(targetObject, targetFieldName);
		String sourceObjectFieldName = field.getName() + DOT + mapNestedAnnotation.fromNested();
		Object sourceObjectFieldValue = Reflections.getFieldValue(sourceObject, sourceObjectFieldName);
		Reflections.setFieldValue(targetObject, targetFieldName, sourceObjectFieldValue);
	}

	private static void throwExceptionWhenTargetFieldIsNotPresent(Object targetObject, String targetFieldName) {
		try {
			Reflections.getFieldValue(targetObject, targetFieldName);
		} catch (AccessToFieldException e) {
			throw new MappingException("Class %s does not contain field %s", targetObject.getClass(), targetFieldName);
		}
	}
}