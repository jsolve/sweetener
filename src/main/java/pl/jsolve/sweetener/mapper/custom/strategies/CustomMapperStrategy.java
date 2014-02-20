package pl.jsolve.sweetener.mapper.custom.strategies;

public interface CustomMapperStrategy {

	Object apply(Object object, Class<?> targetType);
}