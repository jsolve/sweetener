package pl.jsolve.sweetener.mapper.annotationDriven;

public interface AnnotationMapping {

	<S, T> void apply(S sourceObject, T targetObject);
}