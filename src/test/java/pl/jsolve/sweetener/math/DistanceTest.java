package pl.jsolve.sweetener.math;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class DistanceTest {

	@Test
	public void shouldCalculateDistanceBetween2DPoints() {
		// given

		// when
		double distance = Maths.distance(-1, 1, -2, 2);

		// then
		assertThat(distance).isEqualTo(Math.sqrt(2));
	}

	@Test
	public void shouldCalculateDistanceBetween3DPoints() {
		// given

		// when
		double distance = Maths.distance(1, 2, 3, 1, 3, 2);

		// then
		assertThat(distance).isEqualTo(Math.sqrt(2));
	}

	@Test
	public void shouldCalculateDistanceBetween2DPointsForDouble() {
		// given

		// when
		double distance = Maths.distance(-1.0, 1.0, -2.0, 2.0);

		// then
		assertThat(distance).isEqualTo(Math.sqrt(2.0));
	}

	@Test
	public void shouldCalculateDistanceBetween3DPointsForDouble() {
		// given

		// when
		double distance = Maths.distance(1.0, 2.0, 3.0, 1.0, 3.0, 2.0);

		// then
		assertThat(distance).isEqualTo(Math.sqrt(2.0));
	}

}
