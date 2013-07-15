package pl.jsolve.sweetener.mapper.annotationDriven;

interface AnnotationMapping {

	<S, T> void apply(S sourceObject, T targetObject);
}