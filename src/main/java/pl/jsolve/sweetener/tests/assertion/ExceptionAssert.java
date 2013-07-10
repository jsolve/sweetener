package pl.jsolve.sweetener.tests.assertion;

public final class ExceptionAssert {

	private final Throwable throwable;

	ExceptionAssert(Throwable throwable) {
		this.throwable = throwable;
	}

	public ExceptionAssert isNotThrown() {
		if (throwable == null) {
			return this;
		}
		throw new AssertionError("Expected exception NOT to be thrown.");
	}

	public ExceptionAssert isThrown() {
		if (throwable != null) {
			return this;
		}
		throw new AssertionError("Expected exception to be thrown.");
	}

	public ExceptionAssert withMessage(String exceptionMessage) {
		if (throwable == null || throwable.getMessage().equals(exceptionMessage)) {
			return this;
		}
		throw new AssertionError("Expected exception message [" + exceptionMessage + "] but was [" + throwable.getMessage() + "].");
	}

	public ExceptionAssert withMessageContaining(String message) {
		if (throwable == null || throwable.getMessage().contains(message)) {
			return this;
		}
		throw new AssertionError("Expected exception message [" + throwable.getMessage() + "] to contain [" + message + "]");
	}

	public ExceptionAssert as(String string) {
		return this;
	}
}