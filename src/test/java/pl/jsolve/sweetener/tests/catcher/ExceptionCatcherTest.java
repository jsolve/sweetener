package pl.jsolve.sweetener.tests.catcher;

import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

import org.junit.Test;

public class ExceptionCatcherTest {

    @SuppressWarnings("serial")
    class ExpectedException extends Exception {
    }

    @SuppressWarnings("serial")
    class OtherException extends Exception {
    }

    @Test
    public void shouldTryToCatchAnException() {
	// when
	ExpectedException caughtException = tryToCatch(ExpectedException.class, new ExceptionalOperation() {

	    @Override
	    public void operate() throws Exception {
		operationThatWillThrowException();
	    }
	});
	// then
	assertThrowable(caughtException).isThrown();
    }

    private void operationThatWillThrowException() throws ExpectedException {
	throw new ExpectedException();
    }

    @Test
    public void shouldNotCatchNotThrownException() {
	// when
	ExpectedException caughtException = tryToCatch(ExpectedException.class, new ExceptionalOperation() {

	    @Override
	    public void operate() throws Exception {
		operationThatWillNotThrowAnyException();
	    }
	});
	// then
	assertThrowable(caughtException).isNotThrown();
    }

    private void operationThatWillNotThrowAnyException() {
    }

    @Test
    public void shouldNotCatchOtherExceptionThanExpected() {
	// when
	ExpectedException caughtException = tryToCatch(ExpectedException.class, new ExceptionalOperation() {

	    @Override
	    public void operate() throws Exception {
		operationThatWillThrowOtherException();
	    }
	});
	// then
	assertThrowable(caughtException).isNotThrown();
    }

    private void operationThatWillThrowOtherException() throws Exception {
	throw new OtherException();
    }
}