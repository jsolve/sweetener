package pl.jsolve.sweetener.math.random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jsolve.sweetener.math.Generator;
import pl.jsolve.sweetener.math.Maths;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RandomFloatTest {

    @Mock
    private Generator mockedGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnRandomFloatWhenRandomValueIsNearToZero() {
        // given
        float lowerRange = 0;
        float upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.001);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(0.004f);
    }

    @Test
    public void shouldReturnRandomFloatWhenRandomValueIsNearToOne() {
        // given
        float lowerRange = 0;
        float upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.99);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(3.96f);
    }

    @Test
    public void shouldReturnRandomFloatWhenRandomValueIsReallyNearToZero() {
        // given
        float lowerRange = 0;
        float upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isGreaterThan(0.0f).isLessThan(0.5f);
    }

    @Test
    public void shouldReturnRandomFloatWhenRandomValueIsReallyNearToOne() {
        // given
        float lowerRange = 0;
        float upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.999999);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isGreaterThan(3.5f).isLessThan(4.0f);
    }

    @Test
    public void shouldReturnRandomFloatWhenRandomValueIsEqualToHalfOfRange() {
        // given
        float lowerRange = 0;
        float upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isGreaterThan(1.9f).isLessThan(2.0f);
    }

    @Test
    public void shouldReturnRandomFloatWhenRandom2ValueIsNearToZero() {
        // given
        float lowerRange = -1;
        float upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.001);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(-0.997f);
    }

    @Test
    public void shouldReturnRandomFloat2WhenRandomValueIsNearToOne() {
        // given
        float lowerRange = -1;
        float upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.99);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isGreaterThan(1.9f).isLessThan(2.0f);
    }

    @Test
    public void shouldReturnRandomFloat2WhenRandomValueIsReallyNearToZero() {
        // given
        float lowerRange = -1;
        float upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomFloat2WhenRandomValueIsReallyNearToOne() {
        // given
        float lowerRange = -1;
        float upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomFloat2WhenRandomValueIsEqualToHalfOfRange() {
        // given
        float lowerRange = -1;
        float upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.4999546489465);

        // when
        float random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isGreaterThan(0.4f).isLessThan(0.6f);
    }

}