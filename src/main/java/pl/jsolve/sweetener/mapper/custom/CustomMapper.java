package pl.jsolve.sweetener.mapper.custom;

import java.util.List;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.mapper.custom.strategies.AnnotationDrivenMapperStrategy;
import pl.jsolve.sweetener.mapper.custom.strategies.ArrayElementsMapperStrategy;
import pl.jsolve.sweetener.mapper.custom.strategies.CollectionElementsMapperStrategy;
import pl.jsolve.sweetener.mapper.custom.strategies.CustomMapperStrategy;
import pl.jsolve.sweetener.mapper.custom.strategies.TypeConverterStrategy;

public class CustomMapper {

	private final List<CustomMapperStrategy> strategies = Collections.newLinkedList();
	private final Class<?> targetType;

	private CustomMapper(Class<?> targetType) {
		this.targetType = targetType;
	}

	public static CustomMapper toType(Class<?> targetType) {
		return new CustomMapper(targetType);
	}

	public CustomMapper usingAnnotations() {
		strategies.add(new AnnotationDrivenMapperStrategy());
		return this;
	}

	public CustomMapper usingTypeConvertion() {
		strategies.add(new TypeConverterStrategy());
		return this;
	}

	public CustomMapper arrayElementsTo(Class<?> elementsType) {
		strategies.add(new ArrayElementsMapperStrategy(elementsType));
		return this;
	}

	public CustomMapper collectionElementsTo(Class<?> elementsType) {
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