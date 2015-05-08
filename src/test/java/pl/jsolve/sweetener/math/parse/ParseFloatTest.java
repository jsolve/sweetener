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

public class ParseFloatTest {

    @Test
    public void shouldParseFloat() {
        // given
        String value = "15.3";

        // when
        float result = Maths.parseFloat(value);

        // then
        assertThat(result).isEqualTo(15.3f);
    }

    // Default context

    @Test
    public void shouldParseIncorrectFloat() {
        // given
        String value = "1a5";

        // when
        float result = Maths.parseFloat(value);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldParseTooLargeFloat() {
        // given
        String value = "999999999999999999999999999999999999999999999999999999.9999";

        // when
        float result = Maths.parseFloat(value);

        // then
        assertThat(result).isEqualTo(Float.MAX_VALUE);
    }

    @Test
    public void shouldParseTooSmallFloat() {
        // given
        String value = "-999999999999999999999999999999999999999999999999999999.9999";

        // when
        float result = Maths.parseFloat(value);

        // then
        assertThat(result).isEqualTo(Float.MIN_VALUE);
    }

    // Always zero

    @Test
    public void shouldParseIncorrectFloatWhenContextIsALwaysZero() {
        // given
        String value = "1a5";

        // when
        float result = Maths.parseFloat(value, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldParseTooLargeFloatWhenContextIsALwaysZero() {
        // given
        String value = "999999999999999999999999999999999999999999999999999999.9999";

        // when
        float result = Maths.parseFloat(value, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldParseTooSmallFloatWhenContextIsALwaysZero() {
        // given
        String value = "-999999999999999999999999999999999999999999999999999999.9999";

        // when
        float result = Maths.parseFloat(value, ParseContext.ALWAYS_ZERO);

        // then
        assertThat(result).isEqualTo(0);
    }

    // Zero when incorrect

    @Test
    public void shouldParseIncorrectFloatWhenContextIsZeroWhenIncorrect() {
        // given
        String value = "1a5";

        // when
        float result = Maths.parseFloat(value, ParseContext.ZERO_WHEN_INCORRECT);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldParseTooLargeFloatWhenContextIsZeroWhenIncorrect() {
        // given
        final String value = "999999999999999999999999999999999999999999999999999999.9999";

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                float parseFloat = Maths.parseFloat(value, ParseContext.ZERO_WHEN_INCORRECT);
                System.out.println(parseFloat);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
    }

    @Test
    public void shouldParseTooSmallFloatWhenContextIsZeroWhenIncorrect() {
        // given
        final String value = "-999999999999999999999999999999999999999999999999999999.9999";

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseFloat(value, ParseContext.ZERO_WHEN_INCORRECT);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
    }

    // Exception

    @Test
    public void shouldParseIncorrectFloatWhenContextIsException() {
        // given
        final String value = "1a5";

        // when
        ParseException caughtException = tryToCatch(ParseException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseFloat(value, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException).isNotNull();
    }

    @Test
    public void shouldParseTooLargeFloatWhenContextIsException() {
        // given
        final String value = "999999999999999999999999999999999999999999999999999999.9999";

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseFloat(value, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
    }

    @Test
    public void shouldParseTooSmallFloatWhenContextIsException() {
        // given
        final String value = "-999999999999999999999999999999999999999999999999999999.9999";

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.parseFloat(value, ParseContext.EXCEPTION);
            }
        });

        // then
        assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
    }

}
