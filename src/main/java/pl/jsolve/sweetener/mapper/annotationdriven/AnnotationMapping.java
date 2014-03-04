package pl.jsolve.sweetener.mapper.annotationdriven;

public interface AnnotationMapping {

	<S, T> void apply(S sourceObject, T targetObject);
}