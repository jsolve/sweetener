package pl.jsolve.sweetener.mapper.builder;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.mapper.builder.strategies.AnnotationDrivenMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.ArrayElementsMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.CollectionElementsMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.CustomMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.MapKeysAndValuesMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.TypeConverterStrategy;

public class MapperBuilder<T> {

	private final List<CustomMapperStrategy> strategies = Collections.newLinkedList();
	private final Class<?> targetType;

	private MapperBuilder(Class<?> targetType) {
		this.targetType = targetType;
	}

	public static <T> MapperBuilder<T> toType(Class<T> targetType) {
		return new MapperBuilder<>(targetType);
	}

	public MapperBuilder<T> usingAnnotations() {
		strategies.add(new AnnotationDrivenMapperStrategy());
		return this;
	}

	public MapperBuilder<T> usingTypeConvertion() {
		strategies.add(new TypeConverterStrategy());
		return this;
	}

	public MapperBuilder<T> arrayElementsTo(Class<?> elementsType) {
		strategies.add(new ArrayElementsMapperStrategy(elementsType));
		return this;
	}

	public MapperBuilder<T> collectionElementsTo(Class<?> elementsType) {
		strategies.add(new CollectionElementsMapperStrategy(elementsType));
		return this;
	}

	public MapperBuilder<T> mapKeysAndValuesTo(Class<?> keysType, Class<?> valuesType) {
		strategies.add(new MapKeysAndValuesMapperStrategy(keysType, valuesType));
		return this;
	}

	@SuppressWarnings("unchecked")
	public T map(Object object) {
		for (CustomMapperStrategy strategy : strategies) {
			object = strategy.apply(object, targetType);
		}
		return (T) object;
	}
}