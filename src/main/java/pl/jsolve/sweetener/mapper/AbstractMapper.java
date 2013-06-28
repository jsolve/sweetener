package pl.jsolve.sweetener.mapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractMapper<S, T> {

    protected abstract MappingStrategy<S, T> getMappingStrategy();

    public final List<T> map(Collection<S> sources) {
	List<T> targets = new LinkedList<>();
	for (S source : sources) {
	    targets.add(map(source));
	}
	return targets;
    }

    public final T map(S source) {
	return getMappingStrategy().map(source);
    }

    public final List<S> mapReversely(Collection<T> targets) {
	List<S> sources = new LinkedList<>();
	for (T target : targets) {
	    sources.add(mapReversely(target));
	}
	return sources;
    }

    public final S mapReversely(T target) {
	return getMappingStrategy().mapReversely(target);
    }
}