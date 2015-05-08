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

public class ParseByteTest {

    private final static String positiveValue = "195";
    private final static String negativeValue = "-195";

    @Test
    public void shouldParseByte() {
        // given
        String value = "15	";

        // when
        byte result = Maths.parseByte(value);

        // then
        assertThat(result).isEqualTo((byte) 15);
    }

    @Test
    public void shouldParseByteWhenValueIsNull() {
        // given
        String value = null;

        // when
        byte result = Maths.parseByte(value);

        // then
        assertThat(result).isEqualTo((byte) 0);
    }

    // Default context

    @Test
    public void shouldParseIncorrectByte() {
        // given
        String value = "1a5";

        // when
        byte result = Maths.parseByte(value);

        // then
        assertThat(result).isEqualTo((byte) 0);
    }

    @Test
    public void shouldParseTooLargeByte() {
        // given

        // when
        byte result = Maths.parseByte(positiveValue);

        // then
        assertThat(result).isEqualTo(Byte.MAX_VALUE);
    }

    @Test
    public void shouldParseTooSmallByte() {
        // given

        // when
        byte result = Maths.parseByte(negativeValue);

        // then
        assertThat(result).isEqualTo(Byte.MIN_VALUE);
    }

    // Always zero

    @Test
    public void shouldParseIncorrectByteWhenContextIsALwaysZero() {
        // given
        String value = "1a5";

        // when
        byte result = Maths.parseByte(value, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo((byte) 0);
    }

    @Test
    public void shouldParseTooLargeByteWhenContextIsALwaysZero() {
        // given

        // when
        byte result = Maths.parseByte(positiveValue, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo((byte) 0);
    }

    @Test
    public void shouldParseTooSmallByteWhenContextIsALwaysZero() {
        // given

        // when
        byte result = Maths.parseByte(negativeValue, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo((byte) 0);
    }

    // Zero when incorrect

    @Test
    public void shouldParseIncorrectByteWhenContextIsZeroWhenIncorrect() {
        // given
        String value = "1a5";

        // when
        byte result = Maths.parseByte(value, ParseContext.ZERO_WHEN_INCORRECT);

        // then
        assertThat(result).isEqualTo((byte) 0);
    }

    @Test
    public void shouldParseTooLargeByteWhenContextIsZeroWhenIncorrect() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseByte(positiveValue, ParseContext.ZERO_WHEN_INCORRECT);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
    }

    @Test
    public void shouldParseTooSmallByteWhenContextIsZeroWhenIncorrect() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseByte(negativeValue, ParseContext.ZERO_WHEN_INCORRECT);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
    }

    // Exception

    @Test
    public void shouldParseIncorrectByteWhenContextIsException() {
        // given
        final String value = "1a5";

        // when
        ParseException caughtException = tryToCatch(ParseException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseByte(value, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException).isNotNull();
    }

    @Test
    public void shouldParseTooLargeByteWhenContextIsException() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseByte(positiveValue, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
    }

    @Test
    public void shouldParseTooSmallByteWhenContextIsException() {
        // given

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseByte(negativeValue, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
    }
}
