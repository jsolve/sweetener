package pl.jsolve.sweetener.mapper.builder;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.mapper.builder.strategies.AnnotationDrivenMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.ArrayElementsMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.CollectionElementsMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.CustomMapperStrategy;
import pl.jsolve.sweetener.mapper.builder.strategies.TypeConverterStrategy;

public class MapperBuilder {

	private final List<CustomMapperStrategy> strategies = Collections.newLinkedList();
	private final Class<?> targetType;

	private MapperBuilder(Class<?> targetType) {
		this.targetType = targetType;
	}

	public static MapperBuilder toType(Class<?> targetType) {
		return new MapperBuilder(targetType);
	}

	public MapperBuilder usingAnnotations() {
		strategies.add(new AnnotationDrivenMapperStrategy());
		return this;
	}

	public MapperBuilder usingTypeConvertion() {
		strategies.add(new TypeConverterStrategy());
		return this;
	}

	public MapperBuilder arrayElementsTo(Class<?> elementsType) {
		strategies.add(new ArrayElementsMapperStrategy(elementsType));
		return this;
	}

	public MapperBuilder collectionElementsTo(Class<?> elementsType) {
		strategies.add(new CollectionElementsMapperStrategy(elementsType));
		return this;
	}

	public Object map(Object object) {
		for (CustomMapperStrategy strategy : strategies) {
			object = strategy.apply(object, targetType);
		}
		return object;
	}
}