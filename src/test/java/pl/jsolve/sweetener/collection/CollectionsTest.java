package pl.jsolve.sweetener.collection;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aCaptainAmerica;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aHulk;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aRedScull;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.anIronMan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.jsolve.sweetener.exception.InvalidArgumentException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;
import pl.jsolve.sweetener.tests.stub.hero.Hero;

public class CollectionsTest {

	private static final Comparator<Integer> SOME_COMPARATOR = java.util.Collections.reverseOrder();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private List<String> values;

	@Before
	public void setUp() {
		values = new ArrayList<>();
		values.add("A");
		values.add("B");
		values.add("C");
		values.add("D");
		values.add("E");
		values.add("F");
		values.add("G");
		values.add("H");
		values.add("I");
	}

	@Test
	public void shouldThrowExceptionWhenFirstValueIsNegative() {
		// given
		int from = -1;
		int to = 6;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'From' value cannot be negative");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldThrowExceptionWhenFirstParameterIsGreatherThatNumberOfElements() {
		// given
		int from = 15;
		int to = 30;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'From' value cannot be greater than size of collection");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldThrowExceptionWhenFirstValueIsGreatherThenSecondParameter() {
		// given
		int from = 5;
		int to = 1;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'From' value cannot be greater than the 'to' value");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldThrowExceptionWhenSecondParameterIsGreaterThanNumberOfElements() {
		// given
		int from = 5;
		int to = 11;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'To' value cannot be greater than size of collection");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldTruncateCollection() {
		// given
		int from = 1;
		int to = 5;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(5);
		assertThat(truncatedCollection).containsSequence("B", "C", "D", "E", "F");
	}

	@Test
	public void shouldTruncateCollection2() {
		// given
		int from = 0;
		int to = 8;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(9);
		assertThat(truncatedCollection).containsSequence("B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldTruncateCollectionWhenSecondParameterIsNegative() {
		// given
		int from = 0;
		int to = -1;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(9);
		assertThat(truncatedCollection).containsSequence("A", "B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldTruncateCollectionWhenSecondParameterIsNegative2() {
		// given
		int from = 0;
		int to = -8;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(2);
		assertThat(truncatedCollection).containsSequence("A", "B");
	}

	@Test
	public void shouldPaginateTheCollection() {
		// given
		int page = 0;
		int resultsPerPage = 3;

		// when
		Pagination<String> pagination = Collections.paginate(values, page, resultsPerPage);

		// then
		assertThat(pagination.getPage()).isEqualTo(page);
		assertThat(pagination.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(pagination.getTotalElements()).isEqualTo(9);
		assertThat(pagination.getNumberOfPages()).isEqualTo(3);
		assertThat(pagination.getElementsOfPage()).containsOnly("A", "B", "C");
	}

	@Test
	public void shouldPaginateTheCollection2() {
		// given
		int page = 2;
		int resultsPerPage = 4;

		// when
		Pagination<String> pagination = Collections.paginate(values, page, resultsPerPage);

		// then
		assertThat(pagination.getPage()).isEqualTo(page);
		assertThat(pagination.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(pagination.getTotalElements()).isEqualTo(9);
		assertThat(pagination.getNumberOfPages()).isEqualTo(3);
		assertThat(pagination.getElementsOfPage()).containsOnly("I");
	}

	@Test
	public void shouldPaginateTheCollection3() {
		// given
		int page = 0;
		int resultsPerPage = 40;

		// when
		Pagination<String> pagination = Collections.paginate(values, page, resultsPerPage);

		// then
		assertThat(pagination.getPage()).isEqualTo(page);
		assertThat(pagination.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(pagination.getTotalElements()).isEqualTo(9);
		assertThat(pagination.getNumberOfPages()).isEqualTo(1);
		assertThat(pagination.getElementsOfPage()).containsOnly("A", "B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldChopCollection() {
		// given
		int resultsPerPage = 40;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(0);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(1);
		assertThat(choppedElements.getListOfPages()).hasSize(1);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("A", "B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldChopCollection2() {
		// given
		int resultsPerPage = 3;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(0);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("A", "B", "C");
	}

	@Test
	public void shouldChopCollection3() {
		// given
		int resultsPerPage = 3;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);
		choppedElements.setPage(2);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(2);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("G", "H", "I");
	}

	@Test
	public void shouldChopCollection4() {
		// given
		int resultsPerPage = 4;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);
		choppedElements.setPage(2);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(2);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("I");
	}

	@Test
	public void shouldChopCollection5() {
		// given
		int resultsPerPage = 4;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(0);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);

		// first page
		assertThat(choppedElements.getElementsOfPage()).containsOnly("A", "B", "C", "D");

		choppedElements.nextPage();

		// second page
		assertThat(choppedElements.getElementsOfPage()).containsOnly("E", "F", "G", "H");

		choppedElements.nextPage();

		// third page
		assertThat(choppedElements.getElementsOfPage()).containsOnly("I");

	}

	@Test
	public void shouldCreateNewEmptyArrayList() {
		// when
		ArrayList<Object> result = Collections.newArrayList();

		// then
		assertThat(result).isEmpty();
	}

	@Test
	public void shouldCreateNewArrayListWithGivenIterableElements() {
		// given
		Hero captainAmerica = aCaptainAmerica().build();
		Hero ironMan = anIronMan().build();
		Hero hulk = aHulk().build();
		Hero redScull = aRedScull().build();
		Iterable<Hero> elements = Arrays.asList(captainAmerica, ironMan, hulk, redScull);

		// when
		ArrayList<Hero> result = Collections.newArrayList(elements);

		// then
		assertThat(result).containsOnly(captainAmerica, ironMan, hulk, redScull);
	}

	@Test
	public void shouldCreateNewArrayListWithGivenElements() {
		// given
		Hero captainAmerica = aCaptainAmerica().build();
		Hero ironMan = anIronMan().build();
		Hero hulk = aHulk().build();
		Hero redScull = aRedScull().build();

		// when
		ArrayList<Hero> result = Collections.newArrayList(captainAmerica, ironMan, hulk, redScull);

		// then
		assertThat(result).containsOnly(captainAmerica, ironMan, hulk, redScull);
	}

	@Test
	public void shouldCreateNewLinkedList() {
		// when
		LinkedList<Object> result = Collections.newLinkedList();

		// then
		assertThat(result).isEmpty();
	}

	@Test
	public void shouldCreateNewLinkedListWithGivenElements() {
		// given
		Hero captainAmerica = aCaptainAmerica().build();
		Hero ironMan = anIronMan().build();
		Hero hulk = aHulk().build();
		Hero redScull = aRedScull().build();

		// when
		LinkedList<Hero> result = Collections.newLinkedList(captainAmerica, ironMan, hulk, redScull);

		// then
		assertThat(result).containsOnly(captainAmerica, ironMan, hulk, redScull);
	}

	@Test
	public void shouldCreateNewLinkedListWithGivenIterableElements() {
		// given
		Hero captainAmerica = aCaptainAmerica().build();
		Hero ironMan = anIronMan().build();
		Hero hulk = aHulk().build();
		Hero redScull = aRedScull().build();
		Iterable<Hero> elements = Arrays.asList(captainAmerica, ironMan, hulk, redScull);

		// when
		LinkedList<Hero> result = Collections.newLinkedList(elements);

		// then
		assertThat(result).containsOnly(captainAmerica, ironMan, hulk, redScull);
	}

	@Test
	public void shouldCreateNewHashSet() {
		// when
		HashSet<Object> result = Collections.newHashSet();

		// then
		assertThat(result).isEmpty();
	}

	@Test
	public void shouldCreateNewHashSetWithGivenElements() {
		// given
		Hero captainAmerica = aCaptainAmerica().build();
		Hero ironMan = anIronMan().build();
		Hero hulk = aHulk().build();
		Hero redScull = aRedScull().build();

		// when
		HashSet<Hero> result = Collections.newHashSet(captainAmerica, ironMan, hulk, redScull);

		// then
		assertThat(result).containsOnly(captainAmerica, ironMan, hulk, redScull);
	}

	@Test
	public void shouldCreateNewLinkedHashSet() {
		// when
		LinkedHashSet<Object> result = Collections.newLinkedHashSet();

		// then
		assertThat(result).isEmpty();
	}

	@Test
	public void shouldCreateNewLinkedHashSetWithGivenElements() {
		// given
		Hero captainAmerica = aCaptainAmerica().build();
		Hero ironMan = anIronMan().build();
		Hero hulk = aHulk().build();
		Hero redScull = aRedScull().build();

		// when
		LinkedHashSet<Hero> result = Collections.newLinkedHashSet(captainAmerica, ironMan, hulk, redScull);

		// then
		assertThat(result).containsOnly(captainAmerica, ironMan, hulk, redScull);
	}

	@Test
	public void shouldCreateNewTreeSet() {
		// when
		TreeSet<Comparable<?>> result = Collections.newTreeSet();

		// then
		assertThat(result).isEmpty();
	}

	@Test
	public void shouldCreateNewTreeSetWithGivenElements() {
		// when
		TreeSet<Integer> result = Collections.newTreeSet(5, 10, 12, 1);

		// then
		assertThat(result).containsOnly(1, 5, 10, 12);
	}

	@Test
	public void shouldCreateNewTreeSetWithGivenIterableElements() {
		// given
		Iterable<Integer> elements = Arrays.asList(5, 3, 10, 12);

		// when
		TreeSet<Integer> result = Collections.newTreeSet(elements);

		// then
		assertThat(result).contains(5, 3, 10, 12);
	}

	@Test
	public void shouldCreateNewTreeSetWithGivenComparator() {
		// when
		TreeSet<Integer> result = Collections.newTreeSet(SOME_COMPARATOR);

		// then
		assertThat(result).isEmpty();
		assertThat(result.comparator()).isSameAs(SOME_COMPARATOR);
	}

	@Test
	public void shouldNotCreateNewTreeSetWithNullComparator() {
		// given
		final Comparator<Integer> nullIntegerComparator = null;

		// when
		NullPointerException caughtException = tryToCatch(NullPointerException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Collections.newTreeSet(nullIntegerComparator);
			}
		});

		// then
		assertThrowable(caughtException).withMessage("Comparator cannot be null").isThrown();
	}
}