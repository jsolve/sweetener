package pl.jsolve.sweetener.builder;

import static junit.framework.Assert.fail;
import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.stub.hero.HeroBuilder.aHero;

import org.junit.Test;

import pl.jsolve.sweetener.exception.BuilderWithMethodBindingException;
import pl.jsolve.sweetener.tests.stub.hero.Hero;

public class BuilderTest {

    private static final Object ANY_VALUE = new Object();
    private static final String NON_EXISTING_FIELD = "NonExistingField";
    private static final String HERO_FIRST_NAME_FIELD = "firstName";
    private static final String HERO_FIRST_NAME_VALUE = "Bruce";

    @Test
    public void shouldBuildHero() {
        // when
        Hero result = aHero().build();

        // then
        assertThat(result).isNotNull().isInstanceOf(Hero.class);
    }

    @Test
    public void shouldSetHeroFirstNameFieldViaWithMethod() {
        // when
        Hero result = null;
        Exception caughtException = null;
        try {
            result = aHero().with(HERO_FIRST_NAME_FIELD, HERO_FIRST_NAME_VALUE).build();
        } catch (Exception e) {
            caughtException = e;
        }
        // then
        assertThat(caughtException).isNull();
        assertThat(result.getFirstName()).isEqualTo(HERO_FIRST_NAME_VALUE);
    }

    @Test
    public void shouldNotSetNonExistingHeroFieldViaWithMethod() {
        // when
        Exception caughtException = null;
        try {
            aHero().with(NON_EXISTING_FIELD, ANY_VALUE).build();
            fail("Expected exception because of passing non existing field " + NON_EXISTING_FIELD);
        } catch (Exception e) {
            caughtException = e;
        }
        // then
        assertThat(caughtException).isInstanceOf(BuilderWithMethodBindingException.class);
    }
}