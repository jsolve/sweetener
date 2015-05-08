package pl.jsolve.sweetener.math.random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jsolve.sweetener.math.Generator;
import pl.jsolve.sweetener.math.Maths;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RandomShortTest {

    @Mock
    private Generator mockedGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnRandomShortWhenRandomValueIsNearToZero() {
        // given
        short lowerRange = 0;
        short upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.01);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomShortWhenRandomValueIsNearToOne() {
        // given
        short lowerRange = 0;
        short upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomShortWhenRandomValueIsReallyNearToZero() {
        // given
        short lowerRange = 0;
        short upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomShortWhenRandomValueIsReallyNearToOne() {
        // given
        short lowerRange = 0;
        short upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomShortWhenRandomValueIsEqualToHalfOfRange() {
        // given
        short lowerRange = 0;
        short upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((short) 2);
    }

    @Test
    public void shouldReturnRandomShortWhenRandom2ValueIsNearToZero() {
        // given
        short lowerRange = -1;
        short upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.01);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomShort2WhenRandomValueIsNearToOne() {
        // given
        short lowerRange = -1;
        short upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomShort2WhenRandomValueIsReallyNearToZero() {
        // given
        short lowerRange = -1;
        short upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomShort2WhenRandomValueIsReallyNearToOne() {
        // given
        short lowerRange = -1;
        short upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomShort2WhenRandomValueIsEqualToHalfOfRange() {
        // given
        short lowerRange = -1;
        short upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((short) 0);
    }

    @Test
    public void shouldReturnRandomShortWhenRandom3ValueIsNearToZero() {
        // given
        short lowerRange = Short.MIN_VALUE;
        short upperRange = Short.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.00000001);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomShort3WhenRandomValueIsNearToOne() {
        // given
        short lowerRange = Short.MIN_VALUE;
        short upperRange = Short.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.9999999);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomShort3WhenRandomValueIsReallyNearToZero() {
        // given
        short lowerRange = Short.MIN_VALUE;
        short upperRange = Short.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.00000000000000001);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomShort3WhenRandomValueIsReallyNearToOne() {
        // given
        short lowerRange = Short.MIN_VALUE;
        short upperRange = Short.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.9999999999999999);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomShort3WhenRandomValueIsEqualToHalfOfRange() {
        // given
        short lowerRange = Short.MIN_VALUE;
        short upperRange = Short.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.5000001016489465);

        // when
        short random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((short) 0);
    }
}