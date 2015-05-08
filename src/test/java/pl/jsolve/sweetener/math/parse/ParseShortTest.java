package pl.jsolve.sweetener.math.parse;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

import org.junit.Test;

import pl.jsolve.sweetener.exception.OutOfRangeException;
import pl.jsolve.sweetener.exception.OutOfRangeException.Range;
import pl.jsolve.sweetener.exception.ParseException;
import pl.jsolve.sweetener.math.Maths;
import pl.jsolve.sweetener.math.ParseContext;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;

public class ParseShortTest {

    private final static String positiveValue = "32770";
    private final static String negativeValue = "-32770";

    @Test
    public void shouldParseShort() {
        // given
        String value = "15";

        // when
        short result = Maths.parseShort(value);

        // then
        assertThat(result).isEqualTo((short) 15);
    }

    // Default context

    @Test
    public void shouldParseIncorrectShort() {
        // given
        String value = "1a5";

        // when
        short result = Maths.parseShort(value);

        // then
        assertThat(result).isEqualTo((short) 0);
    }

    @Test
    public void shouldParseTooLargeShort() {
        // given

        // when
        short result = Maths.parseShort(positiveValue);

        // then
        assertThat(result).isEqualTo(Short.MAX_VALUE);
    }

    @Test
    public void shouldParseTooSmallShort() {
        // given

        // when
        short result = Maths.parseShort(negativeValue);

        // then
        assertThat(result).isEqualTo(Short.MIN_VALUE);
    }

    // Always zero

    @Test
    public void shouldParseIncorrectShortWhenContextIsALwaysZero() {
        // given
        String value = "1a5";

        // when
        short result = Maths.parseShort(value, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo((short) 0);
    }

    @Test
    public void shouldParseTooLargeShortWhenContextIsALwaysZero() {
        // given

        // when
        short result = Maths.parseShort(positiveValue, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo((short) 0);
    }

    @Test
    public void shouldParseTooSmallShortWhenContextIsALwaysZero() {
        // given

        // when
        short result = Maths.parseShort(negativeValue, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo((short) 0);
    }

    // Zero when incorrect

    @Test
    public void shouldParseIncorrectShortWhenContextIsZeroWhenIncorrect() {
        // given
        String value = "1a5";

        // when
        short result = Maths.parseShort(value, ParseContext.ZERO_WHEN_INCORRECT);

        // then
        assertThat(result).isEqualTo((short) 0);
    }

    @Test
    public void shouldParseTooLargeShortWhenContextIsZeroWhenIncorrect() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseShort(positiveValue, ParseContext.ZERO_WHEN_INCORRECT);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
    }

    @Test
    public void shouldParseTooSmallShortWhenContextIsZeroWhenIncorrect() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseShort(negativeValue, ParseContext.ZERO_WHEN_INCORRECT);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
    }

    // Exception

    @Test
    public void shouldParseIncorrectShortWhenContextIsException() {
        // given
        final String value = "1a5";

        // when
        ParseException caughtException = tryToCatch(ParseException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseShort(value, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException).isNotNull();
    }

    @Test
    public void shouldParseTooLargeShortWhenContextIsException() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseShort(positiveValue, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
    }

    @Test
    public void shouldParseTooSmallShortWhenContextIsException() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseShort(negativeValue, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
    }
}
