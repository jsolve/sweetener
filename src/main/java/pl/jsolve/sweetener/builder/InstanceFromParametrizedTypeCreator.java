package pl.jsolve.sweetener.builder;

import java.lang.reflect.ParameterizedType;

import pl.jsolve.sweetener.exception.InstanceCreationException;

public final class InstanceFromParametrizedTypeCreator {

    private static final int DEFUALT_PARAMETER_INDEX = 0;
    private final Class<?> genericClass;

    public InstanceFromParametrizedTypeCreator(Class<?> genericClass) {
	this.genericClass = genericClass;
    }

    public <T> T newInstance() {
	try {
	    return (T) genericClass.newInstance();
	} catch (Exception e) {
	    throw new InstanceCreationException("Cannot create new instance of class" + genericClass, e);
	}
    }

    public <T> T createObjectFromParametrizedType() {
	return createObjectFromParametrizedType(DEFUALT_PARAMETER_INDEX);
    }

    public <T> T createObjectFromParametrizedType(int parameterIndex) {
	try {
	    return (T) getClassFromParameterizedType(parameterIndex).newInstance();
	} catch (Exception e) {
	    throw new InstanceCreationException("Cannot create new instance of parametrized type", e);
	}
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> getClassFromParameterizedType(int parameterIndex) {
	return (Class<T>) getParameterizedType().getActualTypeArguments()[parameterIndex];
    }

    private ParameterizedType getParameterizedType() {
	return (ParameterizedType) genericClass.getGenericSuperclass();
    }
}