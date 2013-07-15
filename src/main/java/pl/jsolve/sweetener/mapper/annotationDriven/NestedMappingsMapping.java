package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.NestedMappings;

class NestedMappingsMapping implements AnnotationMapping {

	private static final Class<NestedMappings> NESTED_MAPPINGS_ANNOTATION_CLASS = NestedMappings.class;
	private final MapNestedMapping mapNestedMapping;

	public NestedMappingsMapping(MapNestedMapping mapNestedMapping) {
		this.mapNestedMapping = mapNestedMapping;
	}

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, NESTED_MAPPINGS_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapNested[] nestedMappings = field.getAnnotation(NESTED_MAPPINGS_ANNOTATION_CLASS).value();
			for (MapNested nestedMapping : nestedMappings) {
				mapNestedMapping.applyOnFieldWithAnnotation(sourceObject, targetObject, field, nestedMapping);
			}
		}
	}
}