package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;

class MapExactlyToAnnotationMapping extends AbstractAnnotationMapping {

	private static final Class<MapExactlyTo> MAP_EXACTLY_TO_ANNOTATION_CLASS = MapExactlyTo.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_EXACTLY_TO_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapExactlyTo mapExactlyTo = field.getAnnotation(MAP_EXACTLY_TO_ANNOTATION_CLASS);
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapExactlyTo);
		}
	}

	public final <S, T> void applyOnFieldWithAnnotation(S sourceObject, T targetObject, Field field, MapExactlyTo mapExactlyTo) {
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
}