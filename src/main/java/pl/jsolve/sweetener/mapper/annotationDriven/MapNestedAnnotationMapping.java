package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;

class MapNestedAnnotationMapping extends AbstractAnnotationMapping {

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
		if (isMapNestedOfTargetObject(targetObject, mapNestedAnnotation)) {
			String targetFieldName = mapNestedAnnotation.to();
			throwExceptionWhenFieldIsNotPresent(targetObject, targetFieldName);
			String sourceObjectFieldName = getSourceObjectNestedFieldName(field, mapNestedAnnotation);
			throwExceptionWhenFieldIsNotPresent(sourceObject, sourceObjectFieldName);
			Object sourceObjectFieldValue = Reflections.getFieldValue(sourceObject, sourceObjectFieldName);
			Reflections.setFieldValue(targetObject, targetFieldName, sourceObjectFieldValue);
		}
	}

	private <T> boolean isMapNestedOfTargetObject(T targetObject, MapNested mapNestedAnnotation) {
		return Reflections.getClasses(targetObject).contains(mapNestedAnnotation.of());
	}

	private String getSourceObjectNestedFieldName(Field field, MapNested mapNestedAnnotation) {
		return field.getName() + DOT + mapNestedAnnotation.fromNested();
	}
}