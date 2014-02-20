package pl.jsolve.sweetener.mapper.custom.strategies;

import java.lang.reflect.Array;

import pl.jsolve.sweetener.mapper.custom.CustomMapper;

public class ArrayElementsMapperStrategy implements CustomMapperStrategy {

	private final Class<?> elementsType;
	private final CustomMapper mapper;

	public ArrayElementsMapperStrategy(Class<?> elementsType) {
		this.elementsType = elementsType;
		mapper = CustomMapper.toType(elementsType).usingAnnotations().usingTypeConvertion();
	}

	@Override
	public Object apply(Object object, Class<?> targetType) {
		if (object.getClass().isArray()) {
			Object[] sourceArray = (Object[]) object;
			Object[] targetArray = (Object[]) Array.newInstance(elementsType, sourceArray.length);
			int i = 0;
			for (Object element : sourceArray) {
				targetArray[i++] = mapper.map(element);
			}
			return targetArray;
		}
		return object;
	}
}