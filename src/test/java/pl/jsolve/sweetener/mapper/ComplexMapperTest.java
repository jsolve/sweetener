package pl.jsolve.sweetener.mapper;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aCaptainAmerica;

import org.junit.Test;

import pl.jsolve.sweetener.mapper.complex.ComplexMapper;
import pl.jsolve.sweetener.mapper.complex.MappingStrategy;
import pl.jsolve.sweetener.tests.stub.hero.Hero;
import pl.jsolve.sweetener.tests.stub.hero.HeroSnapshot;

public class ComplexMapperTest {

	private static final String SPACE = " ";
	private static final int ANY_NUMBER = 1337;
	private static final Long ID = 2L;

	@Test
	public void shouldMapHeroToHeroSnapshot() {
		// given
		Hero captainAmerica = aCaptainAmerica().withId(ID).build();
		ComplexMapper<Hero, HeroSnapshot> heroToHeroSnapshotMapper = new ComplexMapper<>(new MappingStrategy<Hero, HeroSnapshot>() {

			@Override
			public HeroSnapshot map(Hero source, HeroSnapshot target) {
				target.setName(source.getFirstName() + SPACE + source.getLastName());
				return target;
			}
		});

		// when
		HeroSnapshot result = heroToHeroSnapshotMapper.map(captainAmerica);

		// then
		assertThat(result.getId()).as("id field has mapping annotations").isEqualTo(captainAmerica.getId());
		assertThat(result.getName()).isEqualTo(captainAmerica.getFirstName() + SPACE + captainAmerica.getLastName());
	}

	@Test
	public void shouldMapHeroToHeroSnapshotWithComplexIdMapping() {
		// given
		Hero captainAmerica = aCaptainAmerica().withId(ID).build();
		ComplexMapper<Hero, HeroSnapshot> heroToHeroSnapshotMapper = new ComplexMapper<>(new MappingStrategy<Hero, HeroSnapshot>() {
			@Override
			public HeroSnapshot map(Hero source, HeroSnapshot target) {
				target.setId(source.getId() + ANY_NUMBER);
				return target;
			}
		});
		// when
		HeroSnapshot result = heroToHeroSnapshotMapper.map(captainAmerica);

		// then
		assertThat(result.getId()).isEqualTo(captainAmerica.getId() + ANY_NUMBER);
	}

	@Test
	public void shouldMapHeroSnapshotToHero() {
		// given
		HeroSnapshot heroSnapshot = new HeroSnapshot();
		heroSnapshot.setId(ID);
		heroSnapshot.setName("Johann Schmidt");
		ComplexMapper<HeroSnapshot, Hero> heroSnapshotToHeroMapper = new ComplexMapper<>(new MappingStrategy<HeroSnapshot, Hero>() {

			@Override
			public Hero map(HeroSnapshot source, Hero target) {
				target.setFirstName(source.getName().split(SPACE)[0]);
				target.setLastName(source.getName().split(SPACE)[1]);
				return target;
			}
		});

		// when
		Hero result = heroSnapshotToHeroMapper.map(heroSnapshot);

		// then
		assertThat(result.getId()).as("id field has mapping annotations").isEqualTo(heroSnapshot.getId());
		assertThat(result.getFirstName() + SPACE + result.getLastName()).isEqualTo(heroSnapshot.getName());
	}
}