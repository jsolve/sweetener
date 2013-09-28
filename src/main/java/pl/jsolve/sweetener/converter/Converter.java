package pl.jsolve.sweetener.converter;

public interface Converter<S, T> {

	T convert(S source);
}
