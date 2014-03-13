package pl.jsolve.sweetener.mapper.builder.strategies;

public interface MapperBuilderStrategy {

	Object apply(Object object, Class<?> targetType);
}