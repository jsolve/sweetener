package pl.jsolve.sweetener.converter;

import static java.util.Collections.synchronizedMap;
import static pl.jsolve.sweetener.core.Reflections.getClassesSatisfyingCondition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.jsolve.sweetener.core.Condition;

class ConvertersContainer {

	private final Map<String, Converter<?, ?>> converters = synchronizedMap(new HashMap<String, Converter<?, ?>>());
	private static final Condition<Class<?>> ALL_EXCEPT_OBJECT_CLASS_CONDITION = new Condition<Class<?>>() {
		@Override
		public boolean isSatisfied(Class<?> object) {
			return !Object.class.equals(object);
		}
	};

	public <S, T> void registerConverter(Class<?> s, Class<?> t, Converter<S, T> converter) {
		String converterId = createConverterId(s, t);
		converters.put(converterId, converter);
	}

	public <S, T> void unregisterConverter(Class<S> sourceClass, Class<T> targetClass) {
		String converterId = createConverterId(sourceClass, targetClass);
		converters.remove(converterId);
	}

	private String createConverterId(Class<?> sourceClass, Class<?> targetClass) {
		sourceClass = generalizeClassIfArray(sourceClass);
		return sourceClass.getName() + ":to:" + targetClass.getName();
	}

	private Class<?> generalizeClassIfArray(Class<?> sourceClass) {
		if (sourceClass.isArray()) {
			sourceClass = Object[].class;
		}
		return sourceClass;
	}

	@SuppressWarnings("unchecked")
	public <S, T> Converter<S, T> getSuitableConverter(S source, Class<T> targetClass) {
		Converter<S, T> converter = (Converter<S, T>) getConverter(source.getClass(), targetClass);
		if (converter != null) {
			return converter;
		}
		List<Class<?>> classesAndInterfacesOfSourceClass = getClassesAndInterfacesOf(source.getClass());
		List<Class<?>> classesAndInterfacesOfTargetClass = getClassesAndInterfacesOf(targetClass);

		for (Class<?> s : classesAndInterfacesOfSourceClass) {
			for (Class<?> t : classesAndInterfacesOfTargetClass) {
				converter = (Converter<S, T>) getConverter(s, t);
				if (converter != null) {
					registerConverter(source.getClass(), targetClass, converter);
					return converter;
				}
			}
		}
		for (Class<?> s : classesAndInterfacesOfSourceClass) {
			converter = (Converter<S, T>) getConverter(s, Object.class);
			if (converter != null) {
				registerConverter(source.getClass(), targetClass, converter);
				return converter;
			}
		}
		for (Class<?> t : classesAndInterfacesOfTargetClass) {
			converter = (Converter<S, T>) getConverter(Object.class, t);
			if (converter != null) {
				registerConverter(source.getClass(), targetClass, converter);
				return converter;
			}
		}
		return null;
	}

	private static List<Class<?>> getClassesAndInterfacesOf(Class<?> targetClass) {
		List<Class<?>> classes = getClassesSatisfyingCondition(targetClass, ALL_EXCEPT_OBJECT_CLASS_CONDITION);
		java.util.Collections.addAll(classes, targetClass.getInterfaces());
		return classes;
	}

	public <S, T> Converter<S, T> getConverter(Class<S> sourceClass, Class<T> targetClass) {
		return (Converter<S, T>) converters.get(createConverterId(sourceClass, targetClass));
	}
}