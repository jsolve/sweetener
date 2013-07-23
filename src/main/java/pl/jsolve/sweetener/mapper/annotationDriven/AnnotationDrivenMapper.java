package pl.jsolve.sweetener.mapper.annotationDriven;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

public final class AnnotationDrivenMapper {

	private static final List<AnnotationMapping> MAPPINGS = new LinkedList<>();

	private AnnotationDrivenMapper() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	static {
		registerAnnotationMapping(new MapExactlyToAnnotationMapping());
		registerAnnotationMapping(new MapNestedAnnotationMapping());
	}

	public static void registerAnnotationMapping(AnnotationMapping annotationMapping) {
		if (annotationMapping != null) {
			MAPPINGS.add(annotationMapping);
		}
	}

	public static <T, V> V map(T sourceObject, Class<V> targetClass) {
		throwExceptionWhenIsNotMappableToTargetClass(sourceObject, targetClass);
		V targetObject = Reflections.tryToCreateNewInstance(targetClass);
		applyAllMappings(sourceObject, targetObject);
		return targetObject;
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

	private static <V, T> void applyAllMappings(T sourceObject, V targetObject) {
		for (AnnotationMapping mapping : MAPPINGS) {
			mapping.apply(sourceObject, targetObject);
		}
	}
}