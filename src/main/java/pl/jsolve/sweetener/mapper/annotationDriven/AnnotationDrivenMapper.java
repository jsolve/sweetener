package pl.jsolve.sweetener.mapper.annotationDriven;

import java.util.LinkedList;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

public final class AnnotationDrivenMapper {

	private static final List<AnnotationMapping> MAPPINGS = new LinkedList<>();
	private static final MapExactlyToMapping MAP_EXACTLY_TO_MAPPING = new MapExactlyToMapping();
	private static final MapNestedMapping MAP_NESTED_MAPPING = new MapNestedMapping();
	private static final NestedMappingsMapping NESTED_MAPPINGS_MAPPING = new NestedMappingsMapping(MAP_NESTED_MAPPING);

	static {
		MAPPINGS.add(MAP_EXACTLY_TO_MAPPING);
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

	public static <V, T> void applyAllMappings(T sourceObject, V targetObject) {
		for (AnnotationMapping mapping : MAPPINGS) {
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
		return mappableTo != null && mappableTo.value() == targetClass;
	}
}