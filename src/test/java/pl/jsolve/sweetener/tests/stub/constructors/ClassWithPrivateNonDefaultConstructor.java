package pl.jsolve.sweetener.tests.stub.constructors;

public class ClassWithPrivateNonDefaultConstructor {

	private int value;

	public ClassWithPrivateNonDefaultConstructor(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
