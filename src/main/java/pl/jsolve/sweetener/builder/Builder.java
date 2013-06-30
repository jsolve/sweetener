package pl.jsolve.sweetener.builder;

import static pl.jsolve.sweetener.core.Objects.nullSafeToString;

import java.lang.reflect.Field;

import pl.jsolve.sweetener.exception.BuilderWithMethodBindingException;

public abstract class Builder<T> {

    private final T builtObject;
    private final InstanceFromParametrizedTypeCreator instanceCreator = new InstanceFromParametrizedTypeCreator(
	    getClass());

    protected Builder(T builtObject) {
	this.builtObject = builtObject;
    }

    protected Builder() {
	builtObject = instanceCreator.createObjectFromParametrizedType();
    }

    public final <V> Builder<T> with(String fieldName, V fieldValue) {
	try {
	    setBuildedObjectField(fieldName, fieldValue);
	} catch (Exception e) {
	    throw new BuilderWithMethodBindingException(e, fieldName, nullSafeToString(fieldValue));
	}
	return this;
    }

    private <V> void setBuildedObjectField(String fieldName, V fieldValue) throws NoSuchFieldException,
	    IllegalAccessException {
	Field field = builtObject.getClass().getDeclaredField(fieldName);
	try {
	    field.setAccessible(true);
	    field.set(builtObject, fieldValue);
	} finally {
	    field.setAccessible(false);
	}
    }

    protected final T getBuiltObject() {
	return builtObject;
    }

    public final T build() {
	return builtObject;
    }
}