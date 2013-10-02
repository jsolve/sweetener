package pl.jsolve.sweetener.converter;

import static java.util.Collections.synchronizedMap;
import static pl.jsolve.sweetener.core.Reflections.getClasses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ConvertersContainer {

	private static final String CONVERTERS_KEY_FORMAT = "%s:to:%s";
	private final Map<String, Converter<?, ?>> converters = synchronizedMap(new HashMap<String, Converter<?, ?>>());

	public <S, T> void registerConverter(Class<S> sourceClass, Class<T> targetClass, Converter<S, T> converter) {
		String converterId = createConverterId(sourceClass, targetClass);
		converters.put(converterId, converter);
	}

	public <S, T> void unregisterConverter(Class<S> sourceClass, Class<T> targetClass) {
		String converterId = createConverterId(sourceClass, targetClass);
		converters.remove(converterId);
	}

	private String createConverterId(Class<?> sourceClass, Class<?> targetClass) {
		sourceClass = generalizeClassIfArray(sourceClass);
		return String.format(CONVERTERS_KEY_FORMAT, sourceClass.getName(), targetClass.getName());
	}

	private Class<?> generalizeClassIfArray(Class<?> sourceClass) {
		if (sourceClass.isArray()) {
			sourceClass = Object[].class;
		}
		return sourceClass;
	}

	@SuppressWarnings("unchecked")
	public <S, T> Converter<S, T> getSuitableConverter(S source, Class<T> targetClass) {
		Converter<S, T> converter = null;
		List<Class<?>> classesAndInterfacesOfSourceClass = getClassesAndInterfacesOf(source.getClass());
		List<Class<?>> classesAndInterfacesOfTargetClass = getClassesAndInterfacesOf(targetClass);

		for (Class<?> s : classesAndInterfacesOfSourceClass) {
			for (Class<?> t : classesAndInterfacesOfTargetClass) {
				converter = (Converter<S, T>) getConverter(s, t);
				if (converter != null) {
					return converter;
				}
			}
		}
		for (Class<?> s : classesAndInterfacesOfSourceClass) {
			converter = (Converter<S, T>) getConverter(s, Object.class);
			if (converter != null) {
				return converter;
			}
		}
		for (Class<?> t : classesAndInterfacesOfTargetClass) {
			converter = (Converter<S, T>) getConverter(Object.class, t);
			if (converter != null) {
				return converter;
			}
		}
		return null;
	}

	private static List<Class<?>> getClassesAndInterfacesOf(Class<?> targetClass) {
		List<Class<?>> classes = getClasses(targetClass);
		classes.remove(Object.class);
		java.util.Collections.addAll(classes, targetClass.getInterfaces());
		return classes;
	}

	public <S, T> Converter<S, T> getConverter(Class<S> sourceClass, Class<T> targetClass) {
		return (Converter<S, T>) converters.get(createConverterId(sourceClass, targetClass));
	}
}