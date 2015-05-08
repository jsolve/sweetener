package pl.jsolve.sweetener.math.random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jsolve.sweetener.math.Generator;
import pl.jsolve.sweetener.math.Maths;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RandomLongTest {

    @Mock
    private Generator mockedGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnRandomLongWhenRandomValueIsNearToZero() {
        // given
        long lowerRange = 0;
        long upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.1);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomLongWhenRandomValueIsNearToOne() {
        // given
        long lowerRange = 0;
        long upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomLongWhenRandomValueIsReallyNearToZero() {
        // given
        long lowerRange = 0;
        long upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.00000000000000000000000000000000000000000000000001);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomLongWhenRandomValueIsReallyNearToOne() {
        // given
        long lowerRange = 0;
        long upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomLongWhenRandomValueIsEqualToHalfOfRange() {
        // given
        long lowerRange = 0;
        long upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((long) 2);
    }

    @Test
    public void shouldReturnRandomLongWhenRandom2ValueIsNearToZero() {
        // given
        long lowerRange = -1;
        long upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.1);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomLong2WhenRandomValueIsNearToOne() {
        // given
        long lowerRange = -1;
        long upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomLong2WhenRandomValueIsReallyNearToZero() {
        // given
        long lowerRange = -1;
        long upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomLong2WhenRandomValueIsReallyNearToOne() {
        // given
        long lowerRange = -1;
        long upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomLong2WhenRandomValueIsEqualToHalfOfRange() {
        // given
        long lowerRange = -1;
        long upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((long) 0);
    }

    @Test
    public void shouldReturnRandomLongWhenRandom3ValueIsNearToZero() {
        // given
        long lowerRange = Long.MIN_VALUE + 1;
        long upperRange = Long.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.0000000000000000000000000001);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomLong3WhenRandomValueIsNearToOne() {
        // given
        long lowerRange = Long.MIN_VALUE;
        long upperRange = Long.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.99999999999999999);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomLong3WhenRandomValueIsReallyNearToZero() {
        // given
        long lowerRange = Long.MIN_VALUE;
        long upperRange = Long.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.00000000000000000000000000000001);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomLong3WhenRandomValueIsReallyNearToOne() {
        // given
        long lowerRange = Long.MIN_VALUE;
        long upperRange = Long.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.999999999999999999);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomLong3WhenRandomValueIsEqualToHalfOfRange() {
        // given
        long lowerRange = Long.MIN_VALUE;
        long upperRange = Long.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.50000000000000000000000000);

        // when
        long random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((long) 0);
    }

}