package pl.jsolve.sweetener.collection;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.jsolve.sweetener.exception.InvalidArgumentException;

public class CollectionsTest {

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
}
