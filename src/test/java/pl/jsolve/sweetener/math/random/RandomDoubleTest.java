package pl.jsolve.sweetener.math.random;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.jsolve.sweetener.math.Generator;
import pl.jsolve.sweetener.math.Maths;

public class RandomDoubleTest {

	@Mock
	private Generator mockedGenerator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnRandomDoubleWhenRandomValueIsNearToZero() {
		// given
		double lowerRange = 0;
		double upperRange = 4;

		given(mockedGenerator.generate()).willReturn(0.001);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isEqualTo(0.004);
	}

	@Test
	public void shouldReturnRandomDoubleWhenRandomValueIsNearToOne() {
		// given
		double lowerRange = 0;
		double upperRange = 4;

		given(mockedGenerator.generate()).willReturn(0.99);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isEqualTo(3.96);
	}

	@Test
	public void shouldReturnRandomDoubleWhenRandomValueIsReallyNearToZero() {
		// given
		double lowerRange = 0;
		double upperRange = 4;

		given(mockedGenerator.generate()).willReturn(0.0000000000000000000000000000000000000001);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isGreaterThan(0.0).isLessThan(0.5);
	}

	@Test
	public void shouldReturnRandomDoubleWhenRandomValueIsReallyNearToOne() {
		// given
		double lowerRange = 0;
		double upperRange = 4;

		given(mockedGenerator.generate()).willReturn(0.99999999999);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isGreaterThan(3.5).isLessThan(4.0);
	}

	@Test
	public void shouldReturnRandomDoubleWhenRandomValueIsEqualToHalfOfRange() {
		// given
		double lowerRange = 0;
		double upperRange = 4;

		given(mockedGenerator.generate()).willReturn(0.4897546489465);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isGreaterThan(1.9).isLessThan(2.0);
	}

	@Test
	public void shouldReturnRandomDoubleWhenRandom2ValueIsNearToZero() {
		// given
		double lowerRange = -1;
		double upperRange = 2;

		given(mockedGenerator.generate()).willReturn(0.001);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isEqualTo(-0.997);
	}

	@Test
	public void shouldReturnRandomDouble2WhenRandomValueIsNearToOne() {
		// given
		double lowerRange = -1;
		double upperRange = 2;

		given(mockedGenerator.generate()).willReturn(0.99);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isGreaterThan(1.9).isLessThan(2.0);
	}

	@Test
	public void shouldReturnRandomDouble2WhenRandomValueIsReallyNearToZero() {
		// given
		double lowerRange = -1;
		double upperRange = 2;

		given(mockedGenerator.generate()).willReturn(0.00000000000000000000000000000000000000000000000001);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isEqualTo(lowerRange);
	}

	@Test
	public void shouldReturnRandomDouble2WhenRandomValueIsReallyNearToOne() {
		// given
		double lowerRange = -1;
		double upperRange = 2;

		given(mockedGenerator.generate()).willReturn(0.99999999999999999999999999999999999999999999999999999);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isEqualTo(upperRange);
	}

	@Test
	public void shouldReturnRandomDouble2WhenRandomValueIsEqualToHalfOfRange() {
		// given
		double lowerRange = -1;
		double upperRange = 2;

		given(mockedGenerator.generate()).willReturn(0.4999546489465);

		// when
		double random = Maths.random(lowerRange, upperRange, mockedGenerator);

		// then
		assertThat(random).isGreaterThan(0.4).isLessThan(0.6);
	}

}