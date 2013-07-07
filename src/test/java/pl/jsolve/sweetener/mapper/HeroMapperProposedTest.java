package pl.jsolve.sweetener.mapper;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.stub.hero.HeroBuilder.aHero;

import org.junit.Test;

import pl.jsolve.sweetener.tests.stub.hero.Hero;
import pl.jsolve.sweetener.tests.stub.hero.HeroSnapshot;

public class HeroMapperProposedTest {

	@Test
	public void shouldMapHeroToHeroSnapshot() throws Exception {
		// given
		String nickname = "James";
		Hero hero = aHero().withNickname(nickname).build();

		// when
		HeroSnapshot result = GenericMapper.map(hero, HeroSnapshot.class);

		// then
		assertThat(result.getName()).isEqualTo(nickname);
	}
}