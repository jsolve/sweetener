package pl.jsolve.sweetener.mapper.annotationDriven;

public interface AnnotationMapping {

	public <S, T> void apply(S sourceObject, T targetObject);
}