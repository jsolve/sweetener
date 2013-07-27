package pl.jsolve.sweetener.tests.assertion;

public final class ThrowableAssertions {

	public static ExceptionAssert assertThrowable(Throwable throwable) {
		return new ExceptionAssert(throwable);
	}
}