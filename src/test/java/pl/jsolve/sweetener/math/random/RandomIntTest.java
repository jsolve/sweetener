package pl.jsolve.sweetener.math.random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jsolve.sweetener.math.Generator;
import pl.jsolve.sweetener.math.Maths;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RandomIntTest {

    @Mock
    private Generator mockedGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandomValueIsNearToZero() {
        // given
        int lowerRange = 0;
        int upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.1);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandomValueIsNearToOne() {
        // given
        int lowerRange = 0;
        int upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandomValueIsReallyNearToZero() {
        // given
        int lowerRange = 0;
        int upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandomValueIsReallyNearToOne() {
        // given
        int lowerRange = 0;
        int upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandomValueIsEqualToHalfOfRange() {
        // given
        int lowerRange = 0;
        int upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((int) 2);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandom2ValueIsNearToZero() {
        // given
        int lowerRange = -1;
        int upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.1);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomInteger2WhenRandomValueIsNearToOne() {
        // given
        int lowerRange = -1;
        int upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomInteger2WhenRandomValueIsReallyNearToZero() {
        // given
        int lowerRange = -1;
        int upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomInteger2WhenRandomValueIsReallyNearToOne() {
        // given
        int lowerRange = -1;
        int upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomInteger2WhenRandomValueIsEqualToHalfOfRange() {
        // given
        int lowerRange = -1;
        int upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((int) 0);
    }

    @Test
    public void shouldReturnRandomIntegerWhenRandom3ValueIsNearToZero() {
        // given
        int lowerRange = Integer.MIN_VALUE;
        int upperRange = Integer.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomInteger3WhenRandomValueIsNearToOne() {
        // given
        int lowerRange = Integer.MIN_VALUE;
        int upperRange = Integer.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomInteger3WhenRandomValueIsReallyNearToZero() {
        // given
        int lowerRange = Integer.MIN_VALUE;
        int upperRange = Integer.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomInteger3WhenRandomValueIsReallyNearToOne() {
        // given
        int lowerRange = Integer.MIN_VALUE;
        int upperRange = Integer.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomInteger3WhenRandomValueIsEqualToHalfOfRange() {
        // given
        int lowerRange = Integer.MIN_VALUE;
        int upperRange = Integer.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.5000000000100100000000);

        // when
        int random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((int) 0);
    }

}