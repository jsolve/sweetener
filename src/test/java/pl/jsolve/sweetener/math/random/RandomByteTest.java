package pl.jsolve.sweetener.math.random;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.jsolve.sweetener.math.Generator;
import pl.jsolve.sweetener.math.Maths;

public class RandomByteTest {

    @Mock
    private Generator mockedGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnRandomByteWhenRandomValueIsNearToZero() {
        // given
        byte lowerRange = 0;
        byte upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.01);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomByteWhenRandomValueIsNearToOne() {
        // given
        byte lowerRange = 0;
        byte upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomByteWhenRandomValueIsReallyNearToZero() {
        // given
        byte lowerRange = 0;
        byte upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.00000000000000000000000000000000000000000000000001);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomByteWhenRandomValueIsReallyNearToOne() {
        // given
        byte lowerRange = 0;
        byte upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.99999999999999999999999999999999999999999999999999999);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomByteWhenRandomValueIsEqualToHalfOfRange() {
        // given
        byte lowerRange = 0;
        byte upperRange = 4;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((byte) 2);
    }

    @Test
    public void shouldReturnRandomByteWhenRandom2ValueIsNearToZero() {
        // given
        byte lowerRange = -1;
        byte upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.01);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomByte2WhenRandomValueIsNearToOne() {
        // given
        byte lowerRange = -1;
        byte upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.9);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomByte2WhenRandomValueIsReallyNearToZero() {
        // given
        byte lowerRange = -1;
        byte upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.00000000000000000000000000000000000000000000000001);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomByte2WhenRandomValueIsReallyNearToOne() {
        // given
        byte lowerRange = -1;
        byte upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.99999999999999999999999999999999999999999999999999999);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomByte2WhenRandomValueIsEqualToHalfOfRange() {
        // given
        byte lowerRange = -1;
        byte upperRange = 2;

        given(mockedGenerator.generate()).willReturn(0.4897546489465);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((byte) 0);
    }
    
    @Test
    public void shouldReturnRandomByteWhenRandom3ValueIsNearToZero() {
        // given
        byte lowerRange = Byte.MIN_VALUE;
        byte upperRange = Byte.MAX_VALUE;

        given(mockedGenerator.generate()).willReturn(0.001);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomByte3WhenRandomValueIsNearToOne() {
        // given
        byte lowerRange = Byte.MIN_VALUE;
        byte upperRange = Byte.MAX_VALUE;


        given(mockedGenerator.generate()).willReturn(0.999);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomByte3WhenRandomValueIsReallyNearToZero() {
        // given
        byte lowerRange = Byte.MIN_VALUE;
        byte upperRange = Byte.MAX_VALUE;


        given(mockedGenerator.generate()).willReturn(0.00000000000000000000000000000000000000000000000001);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(lowerRange);
    }

    @Test
    public void shouldReturnRandomByte3WhenRandomValueIsReallyNearToOne() {
        // given
        byte lowerRange = Byte.MIN_VALUE;
        byte upperRange = Byte.MAX_VALUE;


        given(mockedGenerator.generate()).willReturn(0.99999999999999999999999999999999999999999999999999999);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo(upperRange);
    }

    @Test
    public void shouldReturnRandomByte3WhenRandomValueIsEqualToHalfOfRange() {
        // given
        byte lowerRange = Byte.MIN_VALUE;
        byte upperRange = Byte.MAX_VALUE;


        given(mockedGenerator.generate()).willReturn(0.501016489465);

        // when
        byte random = Maths.random(lowerRange, upperRange, mockedGenerator);

        // then
        assertThat(random).isEqualTo((byte) 0);
    }

 

}