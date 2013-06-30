package pl.jsolve.sweetener.core;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.core.Objects.nullSafe;
import static pl.jsolve.sweetener.core.Objects.nullSafeToString;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aCaptainAmerica;

import org.junit.Test;

import pl.jsolve.sweetener.tests.stub.hero.Hero;

public class NullSafeTest {

    private static final int ZERO = 0;
    private static final Object NULL_OBJECT = null;
    private static final Integer NULL_INTEGER = null;
    private static final Hero NULL_HERO = null;

    @Test
    public void shouldToStringNotNullObject() {
	// given
	Hero captainAmerica = aCaptainAmerica().build();

	// when
	String result = nullSafeToString(captainAmerica);

	// then
	assertThat(result).isEqualTo(captainAmerica.toString());
    }
    
    @Test
    public void shouldReturnNewDoubleForNullValue() throws Exception {
	// given
	Double d = null;
	
	// when
	d = Objects.nullSafeDouble(d);
	
	// then
	assertThat(d).isZero();
    }
    
    @Test
    public void shouldReturnNewIntegerForNullValue() throws Exception {
	// given
	Integer value = null;
	
	// when
	value = Objects.nullSafeInteger(value);
	
	// then
	assertThat(value).isZero();
    }

    @Test
    public void shouldToStringBeEmptyStringOnNullObject() {
	// when
	String result = nullSafeToString(NULL_HERO);

	// then
	assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnSameHeroWhenPassedIsNotNull() {
	// given
	Hero captainAmerica = aCaptainAmerica().build();

	// when
	Hero result = nullSafe(captainAmerica, new OnNullBehavior<Hero>() {

	    @Override
	    public Hero onNull() {
		return new Hero();
	    }
	});

	// then
	assertThat(result).isSameAs(captainAmerica);
    }

    @Test
    public void shouldReturnNewHeroWhenPassedIsNull() {
	// when
	Hero result = nullSafe(NULL_HERO, new OnNullBehavior<Hero>() {

	    @Override
	    public Hero onNull() {
		return new Hero();
	    }
	});

	// then
	assertThat(result).isNotNull().isInstanceOf(Hero.class);
    }

    @Test
    public void shouldReturnNewObjectWhenPassedIsNull() {
	// when
	Object result = nullSafe(NULL_OBJECT, new OnNullBehavior<Object>() {

	    @Override
	    public Object onNull() {
		return new Object();
	    }
	});

	// then
	assertThat(result).isNotNull();
    }

    @Test
    public void shouldReturnZeroWhenPassedIntegerIsNull() {
	// when
	Integer result = nullSafe(NULL_INTEGER, new OnNullBehavior<Integer>() {

	    @Override
	    public Integer onNull() {
		return 0;
	    }
	});

	// then
	assertThat(result).isEqualTo(ZERO);
    }
}