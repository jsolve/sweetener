package pl.jsolve.sweetener.mapper.annotationdriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Mappings;
import pl.jsolve.sweetener.mapper.annotationdriven.exception.MappingException;
import pl.jsolve.sweetener.mapper.builder.MapperBuilder;

class MapAnnotationMapping implements AnnotationMapping {

	private static final String NESTING_CHARACTER = ".";
	private static final Class<Map> MAP_ANNOTATION_CLASS = Map.class;
	private static final Class<Mappings> MAPPINGS_ANNOTATION_CLASS = Mappings.class;

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		applyOnFieldsAnnotatedByMap(sourceObject, targetObject);
		applyOnFieldsAnnotatedByMappings(sourceObject, targetObject);
	}

	private <S, T> void applyOnFieldsAnnotatedByMap(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAP_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			Map mapAnnotation = field.getAnnotation(MAP_ANNOTATION_CLASS);
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapAnnotation);
		}
	}

	private <S, T> void applyOnFieldsAnnotatedByMappings(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, MAPPINGS_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			Map[] mapAnntoations = field.getAnnotation(MAPPINGS_ANNOTATION_CLASS).value();
			applyOnFieldWithAnnotations(sourceObject, targetObject, field, mapAnntoations);
		}
	}

	private <S, T> void applyOnFieldWithAnnotations(S sourceObject, T targetObject, Field field, Map... mapAnnotations) {
		for (Map mapAnnotation : mapAnnotations) {
			applyOnFieldWithAnnotation(sourceObject, targetObject, field, mapAnnotation);
		}
	}

	private <S, T> void applyOnFieldWithAnnotation(S sourceObject, T targetObject, Field field, Map mapAnnotation) {
		if (isMappingIntendedForTargetObject(targetObject, mapAnnotation)) {
			String targetFieldName = getTargetFieldName(field, mapAnnotation);
			throwExceptionWhenFieldIsNotPresent(targetObject, targetFieldName);

			String sourceFieldName = getSourceFieldName(field, mapAnnotation);
			throwExceptionWhenFieldIsNotPresent(sourceObject, sourceFieldName);

			Class<?> targetFieldType = Reflections.getFieldType(targetObject, targetFieldName);
			Object sourceFieldValue = Reflections.getFieldValue(sourceObject, sourceFieldName);

			sourceFieldValue = mapObjectToTargetType(sourceFieldValue, targetFieldType, mapAnnotation);
			Reflections.setFieldValue(targetObject, targetFieldName, sourceFieldValue);
		}
	}

	private <T> boolean isMappingIntendedForTargetObject(T targetObject, Map mapAnnotation) {
		return Collections.containsAny(Reflections.getClasses(targetObject), mapAnnotation.of());
	}

	private String getTargetFieldName(Field field, Map mapAnnotation) {
		if (mapAnnotation.to().isEmpty()) {
			return field.getName();
		}
		return mapAnnotation.to();
	}

	private String getSourceFieldName(Field field, Map mapAnnotation) {
		if (mapAnnotation.fromNested().isEmpty()) {
			return field.getName();
		}
		return field.getName() + NESTING_CHARACTER + mapAnnotation.fromNested();
	}

	private void throwExceptionWhenFieldIsNotPresent(Object object, String fieldName) {
		if (!Reflections.isFieldPresent(object, fieldName)) {
			throw new MappingException("%s does not contain field '%s'. Perhaps you have misspelled field name in @Map annotation?",
					object.getClass(), fieldName);
		}
	}

	private Object mapObjectToTargetType(Object object, Class<?> targetType, Map mapAnnotation) {
		if (object != null) {
			Class<?> elementsType = mapAnnotation.elementsAs();
			Class<?> keysType = mapAnnotation.keysAs();
			Class<?> valuesType = mapAnnotation.valuesAs();
			object = MapperBuilder.toType(targetType)
					.arrayElementsTo(elementsType)
					.collectionElementsTo(elementsType).usingAnnotations()
					.mapKeysAndValuesTo(keysType, valuesType)
					.usingTypeConvertion().map(object);
		}
		return object;
	}
}