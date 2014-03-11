package pl.jsolve.sweetener.mapper.builder.strategies;

import java.lang.reflect.Array;

import pl.jsolve.sweetener.mapper.builder.MapperBuilder;

public class ArrayElementsMapperStrategy implements CustomMapperStrategy {

	private final Class<?> elementsType;
	private final MapperBuilder<?> mapper;

	public ArrayElementsMapperStrategy(Class<?> elementsType) {
		this.elementsType = elementsType;
		mapper = MapperBuilder.toType(elementsType).usingAnnotations().usingTypeConvertion();
	}

	@Override
	public Object apply(Object object, Class<?> targetType) {
		if (object.getClass().isArray()) {
			return mapArrayElements((Object[]) object);
		}
		return object;
	}

	private Object mapArrayElements(Object[] sourceArray) {
		Object[] targetArray = (Object[]) Array.newInstance(elementsType, sourceArray.length);
		int i = 0;
		for (Object element : sourceArray) {
			targetArray[i++] = mapper.map(element);
		}
		return targetArray;
	}
}