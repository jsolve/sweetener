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

public class ParseLongTest {

	private static final String positiveValue = "9223372036854775809";
	private static final String negativeValue = "-9223372036854775809";
	
	@Test
	public void shouldParseLong() {
		// given
		String value = "15";

		// when
		long result = Maths.parseLong(value);

		// then
		assertThat(result).isEqualTo(15);
	}

	// Default context

	@Test
	public void shouldParseIncorrectLong() {
		// given
		String value = "1a5";

		// when
		long result = Maths.parseLong(value);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeLong() {
		// given

		// when
		long result = Maths.parseLong(positiveValue);

		// then
		assertThat(result).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void shouldParseTooSmallLong() {
		// given

		// when
		long result = Maths.parseLong(negativeValue);

		// then
		assertThat(result).isEqualTo(Long.MIN_VALUE);
	}

	// Always zero

	@Test
	public void shouldParseIncorrectLongWhenContextIsALwaysZero() {
		// given
		String value = "1a5";

		// when
		long result = Maths.parseLong(value, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeLongWhenContextIsALwaysZero() {
		// given

		// when
		long result = Maths.parseLong(positiveValue, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooSmallLongWhenContextIsALwaysZero() {
		// given

		// when
		long result = Maths.parseLong(negativeValue, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	// Zero when incorrect

	@Test
	public void shouldParseIncorrectLongWhenContextIsZeroWhenIncorrect() {
		// given
		String value = "1a5";

		// when
		long result = Maths.parseLong(value, ParseContext.ZERO_WHEN_INCORRECT);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeLongWhenContextIsZeroWhenIncorrect() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseLong(positiveValue, ParseContext.ZERO_WHEN_INCORRECT);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
	}

	@Test
	public void shouldParseTooSmallLongWhenContextIsZeroWhenIncorrect() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseLong(negativeValue, ParseContext.ZERO_WHEN_INCORRECT);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
	}

	// Exception

	@Test
	public void shouldParseIncorrectLongWhenContextIsException() {
		// given
		final String value = "1a5";

		// when
		ParseException caughtException = tryToCatch(ParseException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseLong(value, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException).isNotNull();
	}

	@Test
	public void shouldParseTooLargeLongWhenContextIsException() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseLong(positiveValue, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
	}

	@Test
	public void shouldParseTooSmallLongWhenContextIsException() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseLong(negativeValue, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
	}
}
