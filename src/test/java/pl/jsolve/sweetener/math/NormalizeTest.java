package pl.jsolve.sweetener.math;

import org.junit.Test;
import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.exception.OutOfRangeException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;

import java.util.Collection;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

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
        final double value = 11.0;
        final double min = -10.0;
        final double max = 10.0;

        // when
        OutOfRangeException caughtException = tryToCatch(OutOfRangeException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maths.normalize(value, min, max);
            }
        });

        // then
        assertThrowable(caughtException).withMessage("The value %f is out of the range: <%f; %f>", value, min, max)
                .isThrown();
    }

    @Test
    public void shouldNormalizeCollectionOfBytes() {
        // given
        List<Byte> values = Collections.newArrayList((byte) 4, (byte) 2, (byte) 0, (byte) 2, (byte) 10, (byte) -6);

        // when
        Collection<Double> normalizedByte = Maths.normalizeByte(values);

        // then
        assertThat(normalizedByte).contains(0.625, 0.5, 0.375, 0.5, 1.0, 0.0);
    }

}
