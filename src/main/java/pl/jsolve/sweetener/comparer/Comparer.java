package pl.jsolve.sweetener.comparer;

public interface Comparer<T extends Comparable> {

    boolean areEqual(T first, T second);
}