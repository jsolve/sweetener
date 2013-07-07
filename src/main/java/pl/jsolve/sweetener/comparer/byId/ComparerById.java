package pl.jsolve.sweetener.comparer.byId;

import pl.jsolve.sweetener.comparer.Comparer;

public class ComparerById<T extends ComparableById> implements Comparer<T> {

    @Override
    public boolean areEqual(T first, T second) {
	return first.getId().equals(second.getId());
    }
}