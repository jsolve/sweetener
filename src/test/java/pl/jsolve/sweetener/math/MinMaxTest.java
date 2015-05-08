package pl.jsolve.sweetener.math;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.exception.InvalidArgumentException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;

public class MinMaxTest {

    @Test
    public void shouldFindMinMaxValue() {
        // given
        List<Byte> values = Collections.newArrayList((byte) 4, (byte) 5, (byte) 6, (byte) -1, (byte) 8, (byte) -3);

        // when
        MinMaxValue<Byte> minMaxByte = Maths.minMaxByte(values);

        // then
        assertThat(minMaxByte.getMin()).isEqualTo((byte) -3);
        assertThat(minMaxByte.getMax()).isEqualTo((byte) 8);
    }

    @Test
    public void shouldThrowExceptionWhenCollectionIsNull() {
        // given
        final List<Byte> values = null;

        // when
        InvalidArgumentException caughtException = tryToCatch(InvalidArgumentException.class,
                new ExceptionalOperation() {

                    @Override
                    public void operate() throws Exception {
                        Maths.minMaxByte(values);
                    }
                });

        // then
        assertThrowable(caughtException).isThrown();
    }

    @Test
    public void shouldFindMinMaxValueForArray() {
        // given

        // when
        MinMaxValue<Integer> minMaxByte = Maths.minMaxInteger(3, -7, 1, 9, 12, -1, 6);

        // then
        assertThat(minMaxByte.getMin()).isEqualTo(-7);
        assertThat(minMaxByte.getMax()).isEqualTo(12);
    }

    @Test
    public void shouldFindMinMaxValueForArrayWithOneValue() {
        // given

        // when
        MinMaxValue<Integer> minMaxByte = Maths.minMaxInteger(3);

        // then
        assertThat(minMaxByte.getMin()).isEqualTo(3);
        assertThat(minMaxByte.getMax()).isEqualTo(3);
    }
}
