package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Mappings;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

class MapAnnotationMapping implements AnnotationMapping {

	private static final String NESTING_CHARACTER = ".";
	private static final Class<Map> MAP_ANNOTATION_CLASS = Map.class;
	private static final Class<Mappings> MAPS_ANNOTATION_CLASS = Mappings.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		applyOnFieldsAnnotatedByMap(sourceObject, targetObject);
		applyOnFieldsAnnotatedByMappings(sourceObject, targetObject);
	}

	private <S, T> void applyOnFieldsAnnotatedByMap(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			Map mapAnnotation = field.getAnnotation(MAP_ANNOTATION_CLASS);
			applyOnFieldWithAnnotations(sourceObject, targetObject, field, mapAnnotation);
		}
	}

	private <S, T> void applyOnFieldsAnnotatedByMappings(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAPS_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			Map[] mapAnntoations = field.getAnnotation(MAPS_ANNOTATION_CLASS).value();
			applyOnFieldWithAnnotations(sourceObject, targetObject, field, mapAnntoations);
		}
	}

	private <S, T> void applyOnFieldWithAnnotations(S sourceObject, T targetObject, Field field, Map... mapAnnotations) {
		for (Map mapAnnotation : mapAnnotations) {
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapAnnotation);
		}
	}

	private <S, T> void applyOnFieldWithAnnotation(S sourceObject, T targetObject, Field field, Map mapAnnotation) {
		if (isMappingOfTargetObject(targetObject, mapAnnotation)) {
			String targetFieldName = getTargetFieldName(field, mapAnnotation);
			throwExceptionWhenFieldIsNotPresent(targetObject, targetFieldName);

			String sourceFieldName = getSourceFieldName(field, mapAnnotation);
			throwExceptionWhenFieldIsNotPresent(sourceObject, sourceFieldName);

			Class<?> targetFieldType = Reflections.getFieldType(targetObject, targetFieldName);
			Object sourceFieldValue = Reflections.getFieldValue(sourceObject, sourceFieldName);
			sourceFieldValue = mapSourceFieldToTargetField(sourceFieldValue, targetFieldType);
			Reflections.setFieldValue(targetObject, targetFieldName, sourceFieldValue);
		}
	}

	private <T> boolean isMappingOfTargetObject(T targetObject, Map mapAnnotation) {
		return Reflections.getClasses(targetObject).contains(mapAnnotation.of());
	}

	private String getTargetFieldName(Field field, Map mapAnnotation) {
		if (!mapAnnotation.to().isEmpty()) {
			return mapAnnotation.to();
		}
		return field.getName();
	}

	private String getSourceFieldName(Field field, Map map) {
		if (!map.fromNested().isEmpty()) {
			return field.getName() + NESTING_CHARACTER + map.fromNested();
		}
		return field.getName();
	}

	private void throwExceptionWhenFieldIsNotPresent(Object object, String fieldName) {
		try {
			Reflections.getFieldValue(object, fieldName);
		} catch (AccessToFieldException e) {
			throw new MappingException(
					"%s does not contain field '%s'. Perhaps you misspelled field name in @Map(...) annotation?",
					object.getClass(), fieldName);
		}
	}

	private Object mapSourceFieldToTargetField(Object sourceFieldValue, Class<?> targetFieldType) {
		if (AnnotationDrivenMapper.isMappableToTargetClass(sourceFieldValue, targetFieldType)) {
			return AnnotationDrivenMapper.map(sourceFieldValue, targetFieldType);
		}
		return sourceFieldValue;
	}
}