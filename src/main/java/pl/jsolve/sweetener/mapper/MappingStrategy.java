package pl.jsolve.sweetener.mapper;

public interface MappingStrategy<S, T> {

	T map(S source, T target);
}