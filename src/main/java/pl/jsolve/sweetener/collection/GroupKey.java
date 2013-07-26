package pl.jsolve.sweetener.collection;

import java.util.Arrays;

public class GroupKey {

	private final Object[] keys;
	
	public GroupKey(Object ... keys) {
		this.keys = keys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(keys);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupKey other = (GroupKey) obj;
		if (!Arrays.equals(keys, other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupKey [keys=" + Arrays.toString(keys) + "]";
	}
	
}
