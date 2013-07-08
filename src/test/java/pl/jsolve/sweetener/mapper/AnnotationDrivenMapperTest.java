package pl.jsolve.sweetener.mapper;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.stub.hero.HeroBuilder.aHero;

import org.junit.Test;

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
	}
}