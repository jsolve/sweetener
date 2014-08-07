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

public class ParseDoubleTest {

	private static final String positiveValue = "999999999999999999999999999999999999999999999999999999999999999999" +
			"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999";

	private static final String negativeValue = "-999999999999999999999999999999999999999999999999999999999999999999" +
			"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
			"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999";

	@Test
	public void shouldParseDouble() {
		// given
		String value = "15.3";

		// when
		double result = Maths.parseDouble(value);

		// then
		assertThat(result).isEqualTo(15.3);
	}

	// Default context

	@Test
	public void shouldParseIncorrectDouble() {
		// given
		String value = "1a5";

		// when
		double result = Maths.parseDouble(value);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeDouble() {
		// given

		// when
		double result = Maths.parseDouble(positiveValue);

		// then
		assertThat(result).isEqualTo(Double.MAX_VALUE);
	}

	@Test
	public void shouldParseTooSmallDouble() {
		// given

		// when
		double result = Maths.parseDouble(negativeValue);

		// then
		assertThat(result).isEqualTo(Double.MIN_VALUE);
	}

	// Always zero

	@Test
	public void shouldParseIncorrectDoubleWhenContextIsALwaysZero() {
		// given
		String value = "1a5";

		// when
		double result = Maths.parseDouble(value, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeDoubleWhenContextIsALwaysZero() {
		// given

		// when
		double result = Maths.parseDouble(positiveValue, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooSmallDoubleWhenContextIsALwaysZero() {
		// given

		// when
		double result = Maths.parseDouble(negativeValue, ParseContext.ALWAYS_ZERO);

		// then
		assertThat(result).isEqualTo(0);
	}

	// Zero when incorrect

	@Test
	public void shouldParseIncorrectDoubleWhenContextIsZeroWhenIncorrect() {
		// given
		String value = "1a5";

		// when
		double result = Maths.parseDouble(value, ParseContext.ZERO_WHEN_INCORRECT);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldParseTooLargeDoubleWhenContextIsZeroWhenIncorrect() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseDouble(positiveValue, ParseContext.ZERO_WHEN_INCORRECT);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
	}

	@Test
	public void shouldParseTooSmallDoubleWhenContextIsZeroWhenIncorrect() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseDouble(negativeValue, ParseContext.ZERO_WHEN_INCORRECT);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
	}

	// Exception

	@Test
	public void shouldParseIncorrectDoubleWhenContextIsException() {
		// given
		final String value = "1a5";

		// when
		ParseException caughtException = tryToCatch(ParseException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseDouble(value, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException).isNotNull();
	}

	@Test
	public void shouldParseTooLargeDoubleWhenContextIsException() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseDouble(positiveValue, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MAX);
	}

	@Test
	public void shouldParseTooSmallDoubleWhenContextIsException() {
		// given

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.parseDouble(negativeValue, ParseContext.EXCEPTION);
			}
		});

		// then
		assertThat(caughtException.getRange()).isEqualTo(Range.MIN);
	}

}
