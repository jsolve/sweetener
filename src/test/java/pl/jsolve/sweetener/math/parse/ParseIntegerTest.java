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

public class ParseIntegerTest {

	private final static String positiveValue = "2147483657";
	private final static String negativeValue = "-2147483649";

	@Test
	public void shouldParseInteger() {
		// given
		String value = "15";

		// when
		int result = Maths.parseInteger(value);

		// then
		assertThat(result).isEqualTo(15);
	}

	// Default context

	@Test
	public void shouldParseIncorrectInteger() {
		// given
		String value = "1a5";

		// when
		int result = Maths.parseInteger(value);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeInteger() {
		// given

		// when
		int result = Maths.parseInteger(positiveValue);

		// then
		assertThat(result).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void shouldParseTooSmallInteger() {
		// given

		// when
		int result = Maths.parseInteger(negativeValue);

		// then
		assertThat(result).isEqualTo(Integer.MIN_VALUE);
	}

	// Always zero

	@Test
	public void shouldParseIncorrectIntegerWhenContextIsALwaysZero() {
		// given
		String value = "1a5";

		// when
		int result = Maths.parseInteger(value, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeIntegerWhenContextIsALwaysZero() {
		// given

		// when
		int result = Maths.parseInteger(positiveValue, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooSmallIntegerWhenContextIsALwaysZero() {
		// given

		// when
		int result = Maths.parseInteger(negativeValue, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	// Zero when incorrect

	@Test
	public void shouldParseIncorrectIntegerWhenContextIsZeroWhenIncorrect() {
		// given
		String value = "1a5";

		// when
		int result = Maths.parseInteger(value, ParseContext.ZERO_WHEN_INCORRECT);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeIntegerWhenContextIsZeroWhenIncorrect() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				int parseInteger = Maths.parseInteger(positiveValue, ParseContext.ZERO_WHEN_INCORRECT);
				System.out.println(parseInteger);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
	}

	@Test
	public void shouldParseTooSmallIntegerWhenContextIsZeroWhenIncorrect() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseInteger(negativeValue, ParseContext.ZERO_WHEN_INCORRECT);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
	}

	// Exception

	@Test
	public void shouldParseIncorrectIntegerWhenContextIsException() {
		// given
		final String value = "1a5";

		// when
		ParseException caughtException = tryToCatch(ParseException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseInteger(value, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException).isNotNull();
	}

	@Test
	public void shouldParseTooLargeIntegerWhenContextIsException() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseInteger(positiveValue, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
	}

	@Test
	public void shouldParseTooSmallIntegerWhenContextIsException() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseInteger(negativeValue, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
	}
}
