package pl.jsolve.sweetener.core;

class ConditionFactory {

	public static <T> Condition<T> createAlwaysSatisfiedCondition() {
		return new Condition<T>() {

			@Override
			public boolean isSatisfied(T object) {
				return true;
			}
		};
	}
}