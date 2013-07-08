package pl.jsolve.sweetener.mapper;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aCaptainAmerica;

import org.junit.Test;

import pl.jsolve.sweetener.tests.stub.hero.Hero;
import pl.jsolve.sweetener.tests.stub.hero.HeroSnapshot;

public class ComplexMapperTest {

	private static final long ID = 2L;

	@Test
	public void shouldMapHeroToHeroSnapshot() {
		// given
		Hero captainAmerica = aCaptainAmerica().withId(ID).build();
		ComplexMapper<Hero, HeroSnapshot> heroToHeroSnapshotMapper = new ComplexMapper<>(new MappingStrategy<Hero, HeroSnapshot>() {

			@Override
			public HeroSnapshot map(Hero source, HeroSnapshot target) {
				target.setName(source.getFirstName() + " " + source.getLastName());
				return target;
			}
		});

		// when
		HeroSnapshot result = heroToHeroSnapshotMapper.map(captainAmerica);

		// then
		assertThat(result.getId()).as("hero uses mapping adnotations").isEqualTo(captainAmerica.getId());
		assertThat(result.getName()).isEqualTo(captainAmerica.getFirstName() + " " + captainAmerica.getLastName());
	}

	@Test
	public void shouldMapHeroToHeroSnapshotWithComplexIdMapping() {
		// given
		Hero captainAmerica = aCaptainAmerica().withId(ID).build();
		ComplexMapper<Hero, HeroSnapshot> heroToHeroSnapshotMapper = new ComplexMapper<>(new MappingStrategy<Hero, HeroSnapshot>() {
			@Override
			public HeroSnapshot map(Hero source, HeroSnapshot target) {
				target.setId(source.getId() * 10);
				return target;
			}
		});
		// when
		HeroSnapshot result = heroToHeroSnapshotMapper.map(captainAmerica);

		// then
		assertThat(result.getId()).isEqualTo(captainAmerica.getId() * 10);
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
				target.setFirstName(source.getName().split(" ")[0]);
				target.setLastName(source.getName().split(" ")[1]);
				return target;
			}
		});

		// when
		Hero result = heroSnapshotToHeroMapper.map(heroSnapshot);

		// then
		assertThat(result.getId()).as("heroSnapshot uses mapping adnotations").isEqualTo(heroSnapshot.getId());
		assertThat(result.getFirstName() + " " + result.getLastName()).isEqualTo(heroSnapshot.getName());
	}
}