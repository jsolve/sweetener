package pl.jsolve.sweetener.mapper.annotationDriven;

import java.lang.reflect.Field;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.ExactlyToMappings;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;

class ExactlyToMappingsAnnotationMapping extends AbstractAnnotationMapping {

	private static final Class<ExactlyToMappings> EXACTLY_TO_MAPPINGS_ANNOTATION_CLASS = ExactlyToMappings.class;
	private final MapExactlyToAnnotationMapping mapExactlyToAnnotationMapping;

	public ExactlyToMappingsAnnotationMapping(MapExactlyToAnnotationMapping mapExactlyToAnnotationMapping) {
		this.mapExactlyToAnnotationMapping = mapExactlyToAnnotationMapping;
	}

	@Override
	public <S, T> void apply(S sourceObject, T targetObject) {
		List<Field> annotatedfields = Reflections.getFieldsAnnotatedBy(sourceObject, EXACTLY_TO_MAPPINGS_ANNOTATION_CLASS);
		for (Field field : annotatedfields) {
			MapExactlyTo[] exactlyToMappings = field.getAnnotation(EXACTLY_TO_MAPPINGS_ANNOTATION_CLASS).value();
			for (MapExactlyTo exactlyToMapping : exactlyToMappings) {
				mapExactlyToAnnotationMapping.applyOnFieldWithAnnotation(sourceObject, targetObject, field, exactlyToMapping);
			}
		}
	}
}
