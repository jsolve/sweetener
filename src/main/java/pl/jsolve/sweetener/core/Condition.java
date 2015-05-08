package pl.jsolve.sweetener.core;

public interface Condition<T> {

    boolean isSatisfied(T object);
}