package pl.jsolve.sweetener.core;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class MathsTest {

	@Test
	public void shouldReturnRandomByte() {
		// given
		byte lowerRange = 0;
		byte upperRange = 4;

		boolean lowerRangeOccurs = false;
		boolean upperRangeOccurs = false;

		for (int i = 0; i < 30; i++) {
			// when
			byte random = Maths.randomByte(lowerRange, upperRange);
			if (random == lowerRange)
				lowerRangeOccurs = true;
			if (random == upperRange)
				upperRangeOccurs = true;

			// then
			assertThat(random).isGreaterThanOrEqualTo(lowerRange).isLessThanOrEqualTo(upperRange);
		}
		assertThat(lowerRangeOccurs).describedAs("lowerRange").isTrue();
		assertThat(upperRangeOccurs).describedAs("upperRange").isTrue();
	}

	@Test
	public void shouldReturnRandomByte2() {
		// given
		byte lowerRange = -1;
		byte upperRange = 2;

		boolean lowerRangeOccurs = false;
		boolean upperRangeOccurs = false;

		for (int i = 0; i < 300; i++) {
			// when
			byte random = Maths.randomByte(lowerRange, upperRange);
			if (random == lowerRange)
				lowerRangeOccurs = true;
			if (random == upperRange)
				upperRangeOccurs = true;

			// then
			assertThat(random).isGreaterThanOrEqualTo(lowerRange).isLessThanOrEqualTo(upperRange);
		}
		assertThat(lowerRangeOccurs).describedAs("lowerRange").isTrue();
		assertThat(upperRangeOccurs).describedAs("upperRange").isTrue();
	}

	@Test
	public void shouldReturnRandomByte3() {
		// given
		byte lowerRange = Byte.MIN_VALUE;
		byte upperRange = Byte.MAX_VALUE;

		boolean lowerRangeOccurs = false;
		boolean upperRangeOccurs = false;

		for (int i = 0; i < 30000; i++) {
			// when
			byte random = Maths.randomByte(lowerRange, upperRange);
			if (random == lowerRange)
				lowerRangeOccurs = true;
			if (random == upperRange)
				upperRangeOccurs = true;

			// then
			assertThat(random).isGreaterThanOrEqualTo(lowerRange).isLessThanOrEqualTo(upperRange);
		}
		assertThat(lowerRangeOccurs).describedAs("lowerRange").isTrue();
		assertThat(upperRangeOccurs).describedAs("upperRange").isTrue();

	}
}
