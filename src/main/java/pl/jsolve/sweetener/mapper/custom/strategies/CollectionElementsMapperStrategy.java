package pl.jsolve.sweetener.mapper.custom.strategies;

import java.lang.reflect.Array;
import java.util.Collection;

import pl.jsolve.sweetener.mapper.custom.CustomMapper;

public class CollectionElementsMapperStrategy implements CustomMapperStrategy {

	private final Class<?> elementsType;
	private final CustomMapper mapper;

	public CollectionElementsMapperStrategy(Class<?> elementsType) {
		this.elementsType = elementsType;
		mapper = CustomMapper.toType(elementsType).usingAnnotations().usingTypeConvertion();
	}

	@Override
	public Object apply(Object object, Class<?> targetType) {
		if (isCollection(object.getClass())) {
			Collection<?> sourceCollection = (Collection<?>) object;
			Object[] targetArray = (Object[]) Array.newInstance(elementsType, sourceCollection.size());
			int i = 0;
			for (Object element : sourceCollection) {
				targetArray[i++] = mapper.map(element);
			}
			return targetArray;
		}
		return object;
	}

	private boolean isCollection(Class<?> clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}
}