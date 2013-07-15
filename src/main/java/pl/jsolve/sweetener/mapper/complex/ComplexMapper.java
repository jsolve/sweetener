package pl.jsolve.sweetener.mapper.complex;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.AnnotationDrivenMapper;

public class ComplexMapper<S, T> {

	private final MappingStrategy<S, T> mappingStrategy;

	public ComplexMapper(MappingStrategy<S, T> mappingStrategy) {
		this.mappingStrategy = mappingStrategy;
	}

	public final T map(S source) {
		T target = tryToExecuteAnnotationDrivenMapper(source, getMapMethodReturnType());
		return mappingStrategy.map(source, target);
	}

	private Class<T> getMapMethodReturnType() {
		return (Class<T>) Reflections.getMethods(mappingStrategy).get(0).getReturnType();
	}

	private <X, V> V tryToExecuteAnnotationDrivenMapper(X source, Class<V> targetClass) {
		if (targetClass != null && AnnotationDrivenMapper.isMappableToTargetClass(source, targetClass)) {
			return AnnotationDrivenMapper.map(source, targetClass);
		}
		return Reflections.tryToCreateNewInstance(targetClass);
	}
}