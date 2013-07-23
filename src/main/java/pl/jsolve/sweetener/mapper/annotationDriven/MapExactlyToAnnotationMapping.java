package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.ExactlyToMappings;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

class MapExactlyToAnnotationMapping implements AnnotationMapping {

	private static final Class<MapExactlyTo> MAP_EXACTLY_TO_ANNOTATION_CLASS = MapExactlyTo.class;
	private static final Class<ExactlyToMappings> EXACTLY_TO_MAPPINGS_ANNOTATION_CLASS = ExactlyToMappings.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		applyOnFieldsAnnotatedByMapExactlyTo(sourceObject, targetObject);
		applyOnFieldsAnnotatedByExactlyToMappings(sourceObject, targetObject);
	}

	private <S, T> void applyOnFieldsAnnotatedByMapExactlyTo(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_EXACTLY_TO_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapExactlyTo mapExactlyTo = field.getAnnotation(MAP_EXACTLY_TO_ANNOTATION_CLASS);
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapExactlyTo);
		}
	}

	private <S, T> void applyOnFieldsAnnotatedByExactlyToMappings(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, EXACTLY_TO_MAPPINGS_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapExactlyTo[] exactlyToMappings = field.getAnnotation(EXACTLY_TO_MAPPINGS_ANNOTATION_CLASS).value();
			for (MapExactlyTo exactlyToMapping : exactlyToMappings) {
				applyOnFieldWithAnnotation(sourceObject, targetObject, field, exactlyToMapping);
			}
		}
	}

	private final <S, T> void applyOnFieldWithAnnotation(S sourceObject, T targetObject, Field field, MapExactlyTo mapExactlyTo) {
		if (isMapExactlyToOfTargetObject(targetObject, mapExactlyTo)) {
			String targetFieldName = mapExactlyTo.value();
			throwExceptionWhenFieldIsNotPresent(targetObject, targetFieldName);
			String sourceObjectFieldName = field.getName();
			Object sourceObjectFieldValue = Reflections.getFieldValue(sourceObject, sourceObjectFieldName);
			Reflections.setFieldValue(targetObject, targetFieldName, sourceObjectFieldValue);
		}
	}

	private <T> boolean isMapExactlyToOfTargetObject(T targetObject, MapExactlyTo mapExactlyTo) {
		return Reflections.getClasses(targetObject).contains(mapExactlyTo.of());
	}

	private void throwExceptionWhenFieldIsNotPresent(Object object, String fieldName) {
		try {
			Reflections.getFieldValue(object, fieldName);
		} catch (AccessToFieldException e) {
			throw new MappingException("%s does not contain field %s", object.getClass(), fieldName);
		}
	}
}