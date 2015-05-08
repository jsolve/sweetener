package pl.jsolve.sweetener.tests.assertion;

import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;

import org.junit.Test;

public class ThrowableAssertionsTest {

    private static final String EXCEPTION_MESSAGE_SUBSTRING = "message";
    private static final String EXCEPTION_MESSAGE = "Exception message";

    @Test
    public void shouldAssertThatExceptionIsThrown() {
        // when
        Exception exception = new Exception();

        // then
        assertThrowable(exception).isThrown();
    }

    @Test
    public void shouldAssertThatExceptionIsNotThrown() {
        // when
        Exception exception = null;

        // then
        assertThrowable(exception).isNotThrown();
    }

    @Test
    public void shouldAssertThatExceptionHasExpectedMessage() {
        // when
        Exception exception = new Exception(EXCEPTION_MESSAGE);

        // then
        assertThrowable(exception).withMessage(EXCEPTION_MESSAGE).isThrown();
    }

    @Test
    public void shouldAssertThatExceptionMessageContainsSequence() {
        // given
        Exception exception = new Exception(EXCEPTION_MESSAGE);

        // then
        assertThrowable(exception).withMessageContaining(EXCEPTION_MESSAGE_SUBSTRING).isThrown();
    }
}