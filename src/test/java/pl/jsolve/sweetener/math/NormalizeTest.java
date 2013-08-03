package pl.jsolve.sweetener.math;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

import org.junit.Test;

import pl.jsolve.sweetener.exception.OutOfRangeException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;

public class NormalizeTest {

	@Test
	public void shouldNormalizeTwoPositiveValues() {
		// given
		byte value = 5;
		byte min = 0;
		byte max = 10;

		// when
		double normalizedValue = Maths.normalize(value, min, max);

		// then
		assertThat(normalizedValue).isEqualTo(0.5);
	}

	@Test
	public void shouldNormalizeTwoNegativeValues() {
		// given
		byte value = -15;
		byte min = -20;
		byte max = -10;

		// when
		double normalizedValue = Maths.normalize(value, min, max);

		// then
		assertThat(normalizedValue).isEqualTo(0.5);
	}

	@Test
	public void shouldNormalizeTwoValues() {
		// given
		byte value = 5;
		byte min = -10;
		byte max = 10;

		// when
		double normalizedValue = Maths.normalize(value, min, max);

		// then
		assertThat(normalizedValue).isEqualTo(0.75);
	}

	@Test
	public void shouldNormalizeTwoValuesWhenValueIsMinValue() {
		// given
		byte value = -10;
		byte min = -10;
		byte max = 10;

		// when
		double normalizedValue = Maths.normalize(value, min, max);

		// then
		assertThat(normalizedValue).isEqualTo(0);
	}

	@Test
	public void shouldNormalizeTwoValuesWhenValueIsMaxValue() {
		// given
		byte value = 10;
		byte min = -10;
		byte max = 10;

		// when
		double normalizedValue = Maths.normalize(value, min, max);

		// then
		assertThat(normalizedValue).isEqualTo(1);
	}

	@Test
	public void shouldThrowExceptionWhenValueIsOutOfRange() {
		// given
		final byte value = 11;
		final byte min = -10;
		final byte max = 10;

		// when
		OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Maths.normalize(value, min, max);
			}
		});

		// then
		assertThrowable(caughtException).withMessage("The value 11 is out of the range: <-10, 10>").isThrown();
	}

}
