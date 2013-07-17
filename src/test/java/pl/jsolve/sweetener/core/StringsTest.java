package pl.jsolve.sweetener.core;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringsTest {

	@Test
	public void shouldJoinCollectionOfStrings() {
		// given
		List<String> strings = new ArrayList<String>();
		strings.add("A");
		strings.add("B");
		strings.add("C");
		strings.add("D");

		// when
		String result = Strings.join(", ", strings);

		// then
		assertThat(result).isEqualTo("A, B, C, D");
	}

	@Test
	public void testName() throws Exception {
		// given
		String sourceObject = "abbababaab";
		String sequence = "ab";

		// when
		int numberOfCccurrences = Strings.numberOfCccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(4);
	}

	@Test
	public void testName2() throws Exception {
		// given
		String sourceObject = "abbababaab";
		String sequence = ".";

		// when
		int numberOfCccurrences = Strings.numberOfCccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(10);
	}

	@Test
	public void testName3() throws Exception {
		// given
		String sourceObject = "abbababaab";
		String sequence = "ab";

		// when
		String result = Strings.removeAllOccurences(sourceObject, sequence);

		// then
		assertThat(result).isEqualTo("ba");
	}

	@Test
	public void testName4() throws Exception {
		// given
		List<Character> symbols = new ArrayList<Character>();
		symbols.add('A');
		symbols.add('B');
		symbols.add('C');
		symbols.add('D');
		symbols.add('E');
		symbols.add('F');
		// when
		for (int i = 0; i < 10; i++) {
			String result = Strings.random(symbols, 40);
			System.out.println(result);
		}
		// then
		//assertThat(result).isEqualTo("ba");
	}

}
