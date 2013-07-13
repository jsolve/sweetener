package pl.jsolve.sweetener.mapper;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;
import static pl.jsolve.sweetener.tests.stub.hero.HeroBuilder.aHero;

import org.junit.Test;

import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.mapper.exception.MappingException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;
import pl.jsolve.sweetener.tests.stub.hero.Hero;
import pl.jsolve.sweetener.tests.stub.hero.HeroSnapshot;

public class AnnotationDrivenMapperTest {

	private static final String NICKNAME = "ironMan";
	private static final long ID = 1L;

	@Test
	public void shouldMapHeroToHeroSnapshot() {
		// given
		Hero hero = aHero().withId(ID).withNickname(NICKNAME).build();

		// when
		HeroSnapshot result = AnnotationDrivenMapper.map(hero, HeroSnapshot.class);

		// then
		assertThat(result.getId()).isEqualTo(hero.getId());
		assertThat(result.getName()).isEqualTo(hero.getNickname());
	}

	@Test
	public void shouldMapHeroSnapshotToHero() {
		// given
		HeroSnapshot heroSnapshot = new HeroSnapshot();
		heroSnapshot.setId(ID);

		// when
		Hero result = AnnotationDrivenMapper.map(heroSnapshot, Hero.class);

		// then
		assertThat(result.getId()).isEqualTo(heroSnapshot.getId());
		assertThat(result.getFirstName()).as("firstName field is not annotated for mapping").isNull();
		assertThat(result.getLastName()).as("lastName field is not annotated for mapping").isNull();
		assertThat(result.getNickname()).as("nickanme field is not annotated for mapping").isNull();
	}

	@Test
	public void shouldThrowExceptionWhenMappingToUnmappableObject() {
		// when
		MappingException caughtException = tryToCatch(MappingException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				AnnotationDrivenMapper.map(new Hero(), Person.class);
			}
		});

		// then
		assertThrowable(caughtException).withMessage(Hero.class + " is not mappable to " + Person.class).isThrown();
	}
}