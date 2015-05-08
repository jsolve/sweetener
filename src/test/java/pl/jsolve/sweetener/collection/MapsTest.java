package pl.jsolve.sweetener.collection;

import org.junit.Test;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

public class MapsTest {

    private static final Comparator<Integer> SOME_COMPARATOR = java.util.Collections.reverseOrder();

    @Test
    public void shouldCreateNewConcurrentMap() {
        // when
        ConcurrentHashMap<Object, Object> result = Maps.newConcurrentMap();

        // then
        assertThat(result).isEmpty();
    }

    private enum SomeEnum {
        SOME_INSTANCE
    }

    @Test
    public void shouldCreateNewEnumMap() {
        // when
        EnumMap<SomeEnum, Object> result = Maps.newEnumMap(SomeEnum.class);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldNotCreateNewEnumMapWhenPassingNullType() {
        // given
        final Class<SomeEnum> nullType = null;

        // when
        NullPointerException caughtException = tryToCatch(NullPointerException.class, new ExceptionalOperation() {

            @Override
            public void operate() throws Exception {
                Maps.newEnumMap(nullType);
            }
        });
        // then
        assertThrowable(caughtException).withMessage("Type cannot be null").isThrown();
    }

    @Test
    public void shouldCreateNewEnumMapWithTheSameMappingsAsGiven() {
        // given
        EnumMap<SomeEnum, Object> mappings = new EnumMap<SomeEnum, Object>(SomeEnum.class);

        // when
        EnumMap<SomeEnum, Object> result = Maps.newEnumMap(mappings);

        // then
        assertThat(result).isEqualTo(mappings);
        assertThat(result).isNotSameAs(mappings);
    }

    @Test
    public void shouldCreateNewHashMap() {
        // when
        HashMap<Object, Object> result = Maps.newHashMap();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldCreateNewHashMapWithTheSameMappingsAsGiven() {
        // given
        HashMap<SomeEnum, Object> mappings = new HashMap<SomeEnum, Object>();

        // when
        HashMap<SomeEnum, Object> result = Maps.newHashMap(mappings);

        // then
        assertThat(result).isEqualTo(mappings);
        assertThat(result).isNotSameAs(mappings);
    }

    @Test
    public void shouldCreateNewIdentityHashMap() {
        // when
        IdentityHashMap<Object, Object> result = Maps.newIdentityHashMap();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldCreateNewLinkedHashMap() {
        // when
        LinkedHashMap<Object, Object> result = Maps.newLinkedHashMap();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldCreateNewLinkedHashMapWithTheSameMappingsAsGiven() {
        // given
        LinkedHashMap<Object, Object> mappings = new LinkedHashMap<Object, Object>();

        // when
        LinkedHashMap<Object, Object> result = Maps.newLinkedHashMap(mappings);

        // then
        assertThat(result).isEqualTo(mappings);
        assertThat(result).isNotSameAs(mappings);
    }

    @Test
    public void shouldCreateNewTreeMap() {
        // when
        TreeMap<Integer, Integer> result = Maps.newTreeMap();

        // then
        assertThat(result).isEmpty();
        assertThat(result.comparator()).isNull();
    }

    @Test
    public void shouldCreateNewTreeMapWithComparator() {
        // when
        TreeMap<Integer, Integer> result = Maps.newTreeMap(SOME_COMPARATOR);

        // then
        assertThat(result).isEmpty();
        assertThat(result.comparator()).isSameAs(SOME_COMPARATOR);
    }

    @Test
    public void shouldCreateNewTreeMapWithInitialMap() {
        // given
        SortedMap<Integer, Integer> mappings = Maps.newTreeMap();
        mappings.put(5, 10);
        mappings.put(3, 20);
        mappings.put(1, 30);

        // when
        TreeMap<Integer, Integer> result = Maps.newTreeMap(mappings);

        // then
        assertThat(result).isEqualTo(mappings);
        assertThat(result.comparator()).isSameAs(mappings.comparator());
    }
}