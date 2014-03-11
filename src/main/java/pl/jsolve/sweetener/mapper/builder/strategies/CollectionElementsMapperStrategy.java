package pl.jsolve.sweetener.mapper.builder.strategies;

import java.lang.reflect.Array;
import java.util.Collection;

import pl.jsolve.sweetener.mapper.builder.MapperBuilder;

public class CollectionElementsMapperStrategy implements CustomMapperStrategy {

	private final Class<?> elementsType;
	private final MapperBuilder<?> mapper;

	public CollectionElementsMapperStrategy(Class<?> elementsType) {
		this.elementsType = elementsType;
		mapper = MapperBuilder.toType(elementsType).usingAnnotations().usingTypeConvertion();
	}

	@Override
	public Object apply(Object object, Class<?> targetType) {
		if (isCollection(object.getClass())) {
			return mapCollectionElements((Collection<?>) object);
		}
		return object;
	}

	private boolean isCollection(Class<?> clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}

	private Object mapCollectionElements(Collection<?> sourceCollection) {
		Object[] targetArray = (Object[]) Array.newInstance(elementsType, sourceCollection.size());
		int i = 0;
		for (Object element : sourceCollection) {
			targetArray[i++] = mapper.map(element);
		}
		return targetArray;
	}
}