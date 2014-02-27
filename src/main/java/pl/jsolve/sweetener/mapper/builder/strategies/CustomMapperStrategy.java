package pl.jsolve.sweetener.mapper.builder.strategies;

public interface CustomMapperStrategy {

	Object apply(Object object, Class<?> targetType);
}