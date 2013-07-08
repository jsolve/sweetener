package pl.jsolve.sweetener.mapper;

import pl.jsolve.sweetener.core.Objects;
import pl.jsolve.sweetener.core.OnNullStrategy;
import pl.jsolve.sweetener.core.Reflections;

public class ComplexMapper<S, T> {

	private final MappingStrategy<S, T> mappingStrategy;

	public ComplexMapper(MappingStrategy<S, T> mappingStrategy) {
		this.mappingStrategy = mappingStrategy;
	}

	public final T map(S source) {
		final Class<T> targetClass = getMapMethodReturnType();
		T target = tryToExecuteAnnotationDrivenMapper(source, targetClass);
		target = Objects.nullSafe(target, new OnNullStrategy<T>() {

			@Override
			public T onNull() {
				return Reflections.tryToCreateNewInstance(targetClass);
			}
		});
		return mappingStrategy.map(source, target);
	}

	private Class<T> getMapMethodReturnType() {
		return (Class<T>) Reflections.getMethods(mappingStrategy).get(0).getReturnType();
	}

	private <X, V> V tryToExecuteAnnotationDrivenMapper(X source, Class<V> targetClass) {
		if (targetClass != null && AnnotationDrivenMapper.isMappableToTargetClass(source, targetClass)) {
			return AnnotationDrivenMapper.map(source, targetClass);
		}
		return null;
	}
}