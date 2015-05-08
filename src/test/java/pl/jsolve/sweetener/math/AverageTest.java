package pl.jsolve.sweetener.math;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.fest.assertions.Delta;
import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;

public class AverageTest {

    @Test
    public void shouldCalculateAverageOfByteCollection() {
        // given
        List<Byte> values = Collections.newArrayList((byte) 4, (byte) 5, (byte) 6, (byte) -1, (byte) 8, (byte) -4);

        // when
        double averageByte = Maths.averageByte(values);

        // then
        assertThat(averageByte).isEqualTo(3.0);
    }

    @Test
    public void shouldCalculateAverageOfByteCollectionWithMaxValues() {
        // given
        List<Byte> values = Collections.newArrayList(Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE);

        // when
        double averageByte = Maths.averageByte(values);

        // then
        assertThat(averageByte).isEqualTo(127.0);
    }

    @Test
    public void shouldCalculateStandardDeviationOfByteCollection() {
        // given
        List<Byte> values = Collections.newArrayList((byte) 4, (byte) 5, (byte) 6, (byte) -1, (byte) 8, (byte) -4);

        // when
        double averageByte = Maths.standardDeviationByte(values);

        // then
        assertThat(averageByte).isEqualTo(4.16, Delta.delta(0.01));
    }

    @Test
    public void shouldCalculateStandardDeviationOfByteCollectionWithMaxValues() {
        // given
        List<Byte> values = Collections.newArrayList(Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE);

        // when
        double averageByte = Maths.standardDeviationByte(values);

        // then
        assertThat(averageByte).isEqualTo(0.0);
    }

}
