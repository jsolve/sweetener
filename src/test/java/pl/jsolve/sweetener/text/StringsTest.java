package pl.jsolve.sweetener.text;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.text.Strings;

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
	public void shouldJoinEmptyCollectionOfStrings() {
		// given
		List<String> strings = new ArrayList<String>();

		// when
		String result = Strings.join(", ", strings);

		// then
		assertThat(result).isEqualTo("");
	}

	@Test
	public void shouldJoinNullCollectionOfStrings() {
		// given
		List<String> strings = null;

		// when
		String result = Strings.join(", ", strings);

		// then
		assertThat(result).isEqualTo("");
	}

	@Test
	public void shouldJoinCollectionOfPeople() {
		// given
		List<Person> people = new ArrayList<Person>();
		people.add(new Person("John", "Deep", 19, null, null));
		people.add(null);
		people.add(new Person("Marry", "Duke", 21, null, null));

		// when
		String result = Strings.join(", ", people);

		// then
		assertThat(result).isEqualTo("Person [name=John, lastName=Deep], null, Person [name=Marry, lastName=Duke]");
	}

	@Test
	public void shouldCountNumberOfOccurrences() {
		// given
		String sourceObject = "abbababaab";
		String sequence = "ab";

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(4);
	}

	@Test
	public void shouldCountNumberOfOccurrencesGivenSequenceAsRegexp() {
		// given
		String sourceObject = "abbab.baab";
		String sequence = ".";

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(10);
	}

	@Test
	public void shouldCountNumberOfOccurrencesGivenSequenceAsPlainText() {
		// given
		String sourceObject = "abbab.abaab";
		String sequence = ".";

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence, true);

		// then
		assertThat(numberOfCccurrences).isEqualTo(1);
	}

	@Test
	public void shouldCountNumberOfOccurrencesGivenSequenceWhenSourceIsEmpty() {
		// given
		String sourceObject = "";
		String sequence = ".";

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(0);
	}

	@Test
	public void shouldCountNumberOfOccurrencesGivenSequenceWhenSourceIsNull() {
		// given
		String sourceObject = null;
		String sequence = ".";

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(0);
	}

	@Test
	public void shouldCountNumberOfOccurrencesGivenSequenceWhenSequenceIsEmpty() {
		// given
		String sourceObject = "abc";
		String sequence = "";

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence, true);

		// then
		assertThat(numberOfCccurrences).isEqualTo(0);
	}

	@Test
	public void shouldCountNumberOfOccurrencesGivenSequenceWhenSequenceIsNull() {
		// given
		String sourceObject = "abc";
		String sequence = null;

		// when
		int numberOfCccurrences = Strings.numberOfOccurrences(sourceObject, sequence);

		// then
		assertThat(numberOfCccurrences).isEqualTo(0);
	}

	@Test
	public void shouldRemoveAllOccurrences() {
		// given
		String sourceObject = "abbababaab";
		String sequence = "ab";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence);

		// then
		assertThat(result).isEqualTo("ba");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharacters() {
		// given
		String sourceObject = "abbababaab";
		String sequence = ".";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence);

		// then
		assertThat(result).isEqualTo("");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenRegexpIsIgnored() {
		// given
		String sourceObject = "abbababaab";
		String sequence = ".";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence, true);

		// then
		assertThat(result).isEqualTo("abbababaab");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenSourceIsEmpty() {
		// given
		String sourceObject = "";
		String sequence = ".";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence, true);

		// then
		assertThat(result).isEqualTo("");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenSourceIsNull() {
		// given
		String sourceObject = null;
		String sequence = ".";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence, true);

		// then
		assertThat(result).isEqualTo(null);
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenSequenceIsEmpty() {
		// given
		String sourceObject = "abbababaab";
		String sequence = "";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence, true);

		// then
		assertThat(result).isEqualTo("abbababaab");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenSequenceIsEmpty2() {
		// given
		String sourceObject = "abbababaab";
		String sequence = "";

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence);

		// then
		assertThat(result).isEqualTo("abbababaab");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenSequenceIsNull() {
		// given
		String sourceObject = "abbababaab";
		String sequence = null;

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence, true);

		// then
		assertThat(result).isEqualTo("abbababaab");
	}

	@Test
	public void shouldRemoveAllOccurrencesForAllCharactersWhenSequenceIsNull2() {
		// given
		String sourceObject = "abbababaab";
		String sequence = null;

		// when
		String result = Strings.removeAllOccurrences(sourceObject, sequence);

		// then
		assertThat(result).isEqualTo("abbababaab");
	}

	@Test
	public void shouldReturnListOfIndexes() {
		// given
		String sourceObject = "abbababaab";
		String sequence = "ab";

		// when
		List<Integer> indexesOf = Strings.indexesOf(sourceObject, sequence);

		// then
		assertThat(indexesOf).contains(0, 3, 5, 8);
	}

	@Test
	public void shouldReturnListOfIndexesWhenSourceIsEmpty() {
		// given
		String sourceObject = "";
		String sequence = "ab";

		// when
		List<Integer> indexesOf = Strings.indexesOf(sourceObject, sequence);

		// then
		assertThat(indexesOf).isEmpty();
	}

	@Test
	public void shouldReturnListOfIndexesWhenSourceIsNull() {
		// given
		String sourceObject = null;
		String sequence = "ab";

		// when
		List<Integer> indexesOf = Strings.indexesOf(sourceObject, sequence);

		// then
		assertThat(indexesOf).isEmpty();
	}

	@Test
	public void shouldReturnListOfIndexesWhenSequenceIsEmpty() {
		// given
		String sourceObject = "abbababaab";
		String sequence = "";

		// when
		List<Integer> indexesOf = Strings.indexesOf(sourceObject, sequence);

		// then
		assertThat(indexesOf).isEmpty();
	}

	@Test
	public void shouldReturnListOfIndexesWhenSequenceIsNull() {
		// given
		String sourceObject = "abbababaab";
		String sequence = null;

		// when
		List<Integer> indexesOf = Strings.indexesOf(sourceObject, sequence);

		// then
		assertThat(indexesOf).isEmpty();
	}

	@Test
	public void shouldGenerateRandomString() {
		// given
		int length = 5;

		// when
		String random = Strings.random(length);

		// then
		assertThat(random).hasSize(length);
	}

	@Test
	public void shouldGenerateRandomStringWhenLengthIsNegative() {
		// given
		int length = -5;

		// when
		String random = Strings.random(length);

		// then
		assertThat(random).hasSize(0);
	}

	@Test
	public void shouldGenerateRandomStringWithGivenCollectionOfSymbols() {
		// given
		List<Character> symbols = new ArrayList<Character>();
		symbols.add('A');
		symbols.add('B');
		symbols.add('C');
		symbols.add('D');
		symbols.add('E');
		symbols.add('F');

		// when
		String result = Strings.random(symbols, 40);
		// then

		assertThat(result).hasSize(40);
	}

	@Test
	public void shouldGenerateRandomStringWithGivenCollectionOfSymbolsWhenCollectionIsEmpty() {
		// given
		List<Character> symbols = new ArrayList<Character>();

		// when
		String result = Strings.random(symbols, 40);
		// then

		assertThat(result).hasSize(0);
	}

	@Test
	public void shouldGenerateRandomStringWithGivenCollectionOfSymbolsWhenCollectionIsNull() {
		// given
		List<Character> symbols = null;

		// when
		String result = Strings.random(symbols, 40);
		// then

		assertThat(result).hasSize(0);
	}

	@Test
	public void shouldPadGivenString() {
		// given
		String sourceObject = "abc";
		String content = "*-";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, content, length);

		// then
		assertThat(result).isEqualTo("abc*-*-");
	}

	@Test
	public void shouldPadGivenStringBySpaces() {
		// given
		String sourceObject = "abc";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, length);

		// then
		assertThat(result).isEqualTo("abc    ");
	}

	@Test
	public void shouldPadGivenStringWhenCIsNull() {
		// given
		String sourceObject = "abc";
		Character c = null;
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length);

		// then
		assertThat(result).isEqualTo("abc    ");
	}

	@Test
	public void shouldPadGivenStringWhenSourceIsNull() {
		// given
		String sourceObject = null;
		Character c = '*';
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length);

		// then
		assertThat(result).isEqualTo("*******");
	}

	@Test
	public void shouldCapitalizeFirstLetter() {
		// given
		String sourceObject = "home sweet home";

		// when
		String result = Strings.capitalize(sourceObject);

		// then
		assertThat(result).isEqualTo("Home sweet home");
	}

	@Test
	public void shouldCapitalizeFirstLetterOfAllWords() {
		// given
		String sourceObject = "home sweet home";

		// when
		String result = Strings.capitalizeAllWords(sourceObject);

		// then
		assertThat(result).isEqualTo("Home Sweet Home");
	}

}
