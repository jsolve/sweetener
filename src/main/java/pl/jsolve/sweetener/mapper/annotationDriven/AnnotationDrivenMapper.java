package pl.jsolve.sweetener.mapper.annotationDriven;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

public final class AnnotationDrivenMapper {

	private static final List<AbstractAnnotationMapping> MAPPINGS = new LinkedList<>();
	private static final MapExactlyToAnnotationMapping MAP_EXACTLY_TO_MAPPING = new MapExactlyToAnnotationMapping();
	private static final MapNestedAnnotationMapping MAP_NESTED_MAPPING = new MapNestedAnnotationMapping();
	private static final NestedMappingsAnnotationMapping NESTED_MAPPINGS_MAPPING = new NestedMappingsAnnotationMapping(MAP_NESTED_MAPPING);
	private static final ExactlyToMappingsAnnotationMapping EXACTLY_TO_MAPPINGS_MAPPING = new ExactlyToMappingsAnnotationMapping(
			MAP_EXACTLY_TO_MAPPING);

	static {
		MAPPINGS.add(MAP_EXACTLY_TO_MAPPING);
		MAPPINGS.add(EXACTLY_TO_MAPPINGS_MAPPING);
		MAPPINGS.add(MAP_NESTED_MAPPING);
		MAPPINGS.add(NESTED_MAPPINGS_MAPPING);
	}

	private AnnotationDrivenMapper() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	public static <T, V> V map(T sourceObject, Class<V> targetClass) {
		throwExceptionWhenIsNotMappableToTargetClass(sourceObject, targetClass);
		V targetObject = Reflections.tryToCreateNewInstance(targetClass);
		applyAllMappings(sourceObject, targetObject);
		return targetObject;
	}

	private static <V, T> void applyAllMappings(T sourceObject, V targetObject) {
		for (AbstractAnnotationMapping mapping : MAPPINGS) {
			mapping.apply(sourceObject, targetObject);
		}
	}

	private static <T, V> void throwExceptionWhenIsNotMappableToTargetClass(T object, Class<V> targetClass) {
		if (!isMappableToTargetClass(object, targetClass)) {
			throw new MappingException("%s is not mappable to %s", object.getClass(), targetClass);
		}
	}

	public static <T, V> boolean isMappableToTargetClass(T object, Class<V> targetClass) {
		MappableTo mappableTo = object.getClass().getAnnotation(MappableTo.class);
		return mappableTo != null && Arrays.asList(mappableTo.value()).contains(targetClass);
	}
}