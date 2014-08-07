package pl.jsolve.sweetener.math;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class AdjustToRangeTest {

	@Test
	public void shouldReturnTheSameValueIfValueIsInRange() {
		// given
		byte value = 5;
		byte min = 0;
		byte max = 10;

		// when
		byte result = Maths.adjustToRange(value, min, max);

		// then
		assertThat(result).isEqualTo((byte) 5);
	}

	@Test
	public void shouldReturnMinValueIfValueIsBelowTheMin() {
		// given
		byte value = -5;
		byte min = 0;
		byte max = 10;

		// when
		byte result = Maths.adjustToRange(value, min, max);

		// then
		assertThat(result).isEqualTo((byte) 0);
	}

	@Test
	public void shouldReturnMinValueIfValueIsAboveTheMin() {
		// given
		byte value = 15;
		byte min = 0;
		byte max = 10;

		// when
		byte result = Maths.adjustToRange(value, min, max);

		// then
		assertThat(result).isEqualTo((byte) 10);
	}
}
