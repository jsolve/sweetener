package pl.jsolve.sweetener.comparer.byid;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aCaptainAmerica;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aHulk;

import org.junit.Test;

import pl.jsolve.sweetener.comparer.Comparer;
import pl.jsolve.sweetener.comparer.byid.ComparableById;
import pl.jsolve.sweetener.comparer.byid.ComparerById;
import pl.jsolve.sweetener.tests.stub.hero.Hero;

public class ComparerByIdTest {

	private static final Long ID = 1L;
	private static final Long SAME_ID = 1L;
	private static final Long DIFFERENT_ID = 2L;

	@Test
	public void shouldCompareTwoHeroesWithSameId() {
		// given
		Comparer<Hero> heroesComparerById = new ComparerById<>();
		Hero hulk = aHulk().withId(ID).build();
		Hero captainAmerica = aCaptainAmerica().withId(SAME_ID).build();

		// when
		boolean result = heroesComparerById.areEqual(hulk, captainAmerica);

		// then
		assertThat(result).as("both captainAmerica and hulk use the same id").isTrue();
	}

	@Test
	public void shouldCompareTwoHeroesWithDifferentIds() {
		// given
		Comparer<Hero> heroesComparerById = new ComparerById<>();
		Hero hulk = aHulk().withId(ID).build();
		Hero captainAmerica = aCaptainAmerica().withId(DIFFERENT_ID).build();

		// when
		boolean result = heroesComparerById.areEqual(hulk, captainAmerica);

		// then
		assertThat(result).as("captainAmerica and hulk use different ids").isFalse();
	}

	@Test
	public void shouldCompareTwoComparableByIdObjectsWithSameId() {
		// given
		Comparer<ComparableById> comparerById = new ComparerById<>();
		ComparableById comparableById = createMockedComparableByIdObjectWithId(ID);
		ComparableById comparableByIdWithSameId = createMockedComparableByIdObjectWithId(SAME_ID);

		// when
		boolean result = comparerById.areEqual(comparableById, comparableByIdWithSameId);

		// then
		assertThat(result).as("both comparable by id objects use the same id").isTrue();
	}

	@Test
	public void shouldCompareTwoComparableByIdObjectsWithDifferentIds() {
		// given
		Comparer<ComparableById> comparerById = new ComparerById<>();
		ComparableById comparableById = createMockedComparableByIdObjectWithId(ID);
		ComparableById comparableByIdWithDifferentId = createMockedComparableByIdObjectWithId(DIFFERENT_ID);

		// when
		boolean result = comparerById.areEqual(comparableById, comparableByIdWithDifferentId);

		// then
		assertThat(result).as("comparable objects use different ids").isFalse();
	}

	private ComparableById createMockedComparableByIdObjectWithId(Long id) {
		ComparableById comparableById = mock(ComparableById.class);
		given(comparableById.getId()).willReturn(id);
		return comparableById;
	}
}
