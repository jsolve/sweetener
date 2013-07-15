package pl.jsolve.sweetener.mapper.complex;

public interface MappingStrategy<S, T> {

	T map(S source, T target);
}