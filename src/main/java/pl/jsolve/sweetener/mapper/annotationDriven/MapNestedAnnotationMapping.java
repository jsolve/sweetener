package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.NestedMappings;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

class MapNestedAnnotationMapping implements AnnotationMapping {

	private static final String DOT = ".";
	private static final Class<MapNested> MAP_NESTED_ANNOTATION_CLASS = MapNested.class;
	private static final Class<NestedMappings> NESTED_MAPPINGS_ANNOTATION_CLASS = NestedMappings.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		applyOnFieldsAnnotatedByMapNested(sourceObject, targetObject);
		applyOnFieldsAnnotatedByNestedMappings(sourceObject, targetObject);
	}

	private <S, T> void applyOnFieldsAnnotatedByNestedMappings(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, NESTED_MAPPINGS_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapNested[] nestedMappings = field.getAnnotation(NESTED_MAPPINGS_ANNOTATION_CLASS).value();
			for (MapNested nestedMapping : nestedMappings) {
				applyOnFieldWithAnnotation(sourceObject, targetObject, field, nestedMapping);
			}
		}
	}

	private <T, S> void applyOnFieldsAnnotatedByMapNested(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_NESTED_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapNested mapNestedAnnotation = field.getAnnotation(MAP_NESTED_ANNOTATION_CLASS);
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapNestedAnnotation);
		}
	}

	private final <S, T> void applyOnFieldWithAnnotation(S sourceObject, T targetObject, Field field, MapNested mapNestedAnnotation) {
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

	private void throwExceptionWhenFieldIsNotPresent(Object object, String fieldName) {
		try {
			Reflections.getFieldValue(object, fieldName);
		} catch (AccessToFieldException e) {
			throw new MappingException("%s does not contain field %s", object.getClass(), fieldName);
		}
	}

	private String getSourceObjectNestedFieldName(Field field, MapNested mapNestedAnnotation) {
		return field.getName() + DOT + mapNestedAnnotation.fromNested();
	}
}