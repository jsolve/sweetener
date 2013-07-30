package pl.jsolve.sweetener.text;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.exception.InvalidArgumentException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;

public class StringsTest {

	@Test
	public void shouldJoinCollectionOfStrings() {
		// given
		List<String> strings = new ArrayList<>();
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
		List<String> strings = new ArrayList<>();

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
		List<Person> people = new ArrayList<>();
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
		List<Character> symbols = new ArrayList<>();
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
		List<Character> symbols = new ArrayList<>();

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
	public void shouldRightPadGivenString() {
		// given
		String sourceObject = "abc";
		String content = "*-";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, content, length, PaddingType.RIGHT);

		// then
		assertThat(result).isEqualTo("abc*-*-");
	}

	@Test
	public void shouldLeftPadGivenString() {
		// given
		String sourceObject = "abc";
		String content = "*-";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, content, length, PaddingType.LEFT);

		// then
		assertThat(result).isEqualTo("-*-*abc");
	}

	@Test
	public void shouldCentrePadGivenString() {
		// given
		String sourceObject = "abc";
		String content = "*-";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, content, length, PaddingType.CENTRE);

		// then
		assertThat(result).isEqualTo("-*abc*-");
	}

	@Test
	public void shouldCentrePadGivenString2() {
		// given
		String sourceObject = "abc";
		String content = "*-";
		int length = 10;

		// when
		String result = Strings.pad(sourceObject, content, length, PaddingType.CENTRE);

		// then
		assertThat(result).isEqualTo("*-*abc*-*-");
	}

	@Test
	public void shouldRightPadGivenStringBySpaces() {
		// given
		String sourceObject = "abc";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, length, PaddingType.RIGHT);

		// then
		assertThat(result).isEqualTo("abc    ");
	}

	@Test
	public void shouldLeftPadGivenStringBySpaces() {
		// given
		String sourceObject = "abc";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, length, PaddingType.LEFT);

		// then
		assertThat(result).isEqualTo("    abc");
	}

	@Test
	public void shouldCentrePadGivenStringBySpaces() {
		// given
		String sourceObject = "abc";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, length, PaddingType.CENTRE);

		// then
		assertThat(result).isEqualTo("  abc  ");
	}

	@Test
	public void shouldRightPadGivenStringWhenCIsNull() {
		// given
		String sourceObject = "abc";
		Character c = null;
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length, PaddingType.RIGHT);

		// then
		assertThat(result).isEqualTo("abc    ");
	}

	@Test
	public void shouldLeftPadGivenStringWhenCIsNull() {
		// given
		String sourceObject = "abc";
		Character c = null;
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length, PaddingType.LEFT);

		// then
		assertThat(result).isEqualTo("    abc");
	}

	@Test
	public void shouldCentrePadGivenStringWhenCIsNull() {
		// given
		String sourceObject = "abc";
		Character c = null;
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length, PaddingType.CENTRE);

		// then
		assertThat(result).isEqualTo("  abc  ");
	}

	@Test
	public void shouldRightPadGivenStringWhenSourceIsNull() {
		// given
		String sourceObject = null;
		Character c = '*';
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length, PaddingType.RIGHT);

		// then
		assertThat(result).isEqualTo("*******");
	}

	@Test
	public void shouldLeftPadGivenStringWhenSourceIsNull() {
		// given
		String sourceObject = null;
		Character c = '*';
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length, PaddingType.LEFT);

		// then
		assertThat(result).isEqualTo("*******");
	}

	@Test
	public void shouldCentrePadGivenStringWhenSourceIsNull() {
		// given
		String sourceObject = null;
		Character c = '*';
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, c, length, PaddingType.CENTRE);

		// then
		assertThat(result).isEqualTo("*******");
	}

	@Test
	public void shouldRightPadGivenStringBySpacesWhenPaddingIsNull() {
		// given
		String sourceObject = "abc";
		int length = 7;

		// when
		String result = Strings.pad(sourceObject, length, null);

		// then
		assertThat(result).isEqualTo("abc    ");
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

	@Test
	public void shouldReturnTrueIfStringContainsOnlyGivenCharacters() {
		// given
		List<Character> characters = Collections.newArrayList('a', 'b', 'c', 'd', 'e', ' ');

		// when
		boolean containsOnly = Strings.containsOnly("abc de     dabceaaa", characters);

		// then
		assertThat(containsOnly).isTrue();
	}

	@Test
	public void shouldReturnTrueIfStringIsEmpty() {
		// given
		List<Character> characters = Collections.newArrayList('a', 'b', 'c', 'd', 'e', ' ');

		// when
		boolean containsOnly = Strings.containsOnly("", characters);

		// then
		assertThat(containsOnly).isTrue();
	}

	@Test
	public void shouldReturnTrueIfStringIsNull() {
		// given
		List<Character> characters = Collections.newArrayList('a', 'b', 'c', 'd', 'e', ' ');

		// when
		boolean containsOnly = Strings.containsOnly(null, characters);

		// then
		assertThat(containsOnly).isTrue();
	}

	@Test
	public void shouldReturnTrueIfCollectionIsEmptyAndStringIsEmpty() {
		// given
		List<Character> characters = Collections.newArrayList();

		// when
		boolean containsOnly = Strings.containsOnly("", characters);

		// then
		assertThat(containsOnly).isTrue();
	}

	@Test
	public void shouldReturnFalseIfStringNotContainsGivenCharacters() {
		// given
		List<Character> characters = Collections.newArrayList('a', 'b', 'c', 'd', 'e');

		// when
		boolean containsOnly = Strings.containsOnly("abc de     dabceaaa", characters);

		// then
		assertThat(containsOnly).isFalse();
	}

	@Test
	public void shouldReturnFalseWhenListOfCharactersIsEmptyButStringIsNotEmpty() {
		// given
		List<Character> characters = Collections.newArrayList();

		// when
		boolean containsOnly = Strings.containsOnly("abc de     dabceaaa", characters);

		// then
		assertThat(containsOnly).isFalse();
	}

	@Test
	public void shouldThrowExceptionWhenListOfCharactersIsNullButStringIsNotEmpty() {
		// given
		final List<Character> characters = null;

		// when
		InvalidArgumentException caughtException = tryToCatch(InvalidArgumentException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				Strings.containsOnly("abc de     dabceaaa", characters);
			}
		});

		// then
		assertThrowable(caughtException).withMessage("List of characters cannot be null").isThrown();
	}

	@Test
	public void shouldReturnTrueIfValueIsNull() {
		// given
		String value = null;

		// when
		boolean empty = Strings.isEmpty(value);

		// then
		assertThat(empty).isTrue();
	}

	@Test
	public void shouldReturnTrueIfValueIsEmpty() {
		// given
		String value = "\n 	\r\n\t		 	";

		// when
		boolean empty = Strings.isEmpty(value);

		// then
		assertThat(empty).isTrue();
	}

	@Test
	public void shouldTransformStringToOneLine() {
		// given
		String threeLine = "First line\nSecond line\r\nThird line\r\n";

		// when
		String oneLine = Strings.singleLine(threeLine);

		// then
		assertThat(oneLine).isEqualTo("First lineSecond lineThird line");
	}

	@Test
	public void shouldTransformStringToOneLineWhenStringIsNull() {
		// given
		String nullString = null;

		// when
		String oneLine = Strings.singleLine(nullString);

		// then
		assertThat(oneLine).isNull();
	}

	@Test
	public void shouldTransformStringToOneLineWhenStringIsEmpty() {
		// given
		String nullString = "";

		// when
		String oneLine = Strings.singleLine(nullString);

		// then
		assertThat(oneLine).isEqualTo("");
	}

	@Test
	public void shouldReturnStringWithoutNewLinesOnTheEnd() {
		// given
		String stringWithNewLines = "First line\r\n";

		// when
		String stringWithoutNewLines = Strings.removeNewLines(stringWithNewLines);

		// then
		assertThat(stringWithoutNewLines).isEqualTo("First line");
	}

	@Test
	public void shouldReturnStringWithoutNewLinesOnTheEnd2() {
		// given
		String stringWithNewLines = "First line";

		// when
		String stringWithoutNewLines = Strings.removeNewLines(stringWithNewLines);

		// then
		assertThat(stringWithoutNewLines).isEqualTo("First line");
	}

	@Test
	public void shouldReturnStringWithoutNewLinesOnTheEndWhenStringIsEmpty() {
		// given
		String stringWithNewLines = "";

		// when
		String stringWithoutNewLines = Strings.removeNewLines(stringWithNewLines);

		// then
		assertThat(stringWithoutNewLines).isEqualTo("");
	}

	@Test
	public void shouldReturnStringWithoutNewLinesOnTheEndWhenStringContainsSpace() {
		// given
		String stringWithNewLines = " ";

		// when
		String stringWithoutNewLines = Strings.removeNewLines(stringWithNewLines);

		// then
		assertThat(stringWithoutNewLines).isEqualTo(" ");
	}

	@Test
	public void shouldReturnStringWithoutNewLinesOnTheEndWhenStringContainsOnlyNewLine() {
		// given
		String stringWithNewLines = "\n";

		// when
		String stringWithoutNewLines = Strings.removeNewLines(stringWithNewLines);

		// then
		assertThat(stringWithoutNewLines).isEqualTo("");
	}

	@Test
	public void shouldReverseValue() {
		// given
		String stringToRevers = "abcd";

		// when
		String reversedString = Strings.reverse(stringToRevers);

		// then
		assertThat(reversedString).isEqualTo("dcba");
	}

	@Test
	public void shouldReturnNullWhenStringToReverseIsNull() {
		// given
		String stringToRevers = null;

		// when
		String nullString = Strings.reverse(stringToRevers);

		// then
		assertThat(nullString).isNull();
	}

	@Test
	public void shouldReturnEmptyWhenStringToReverseIsEmpty() {
		// given
		String stringToRevers = "";

		// when
		String emptyString = Strings.reverse(stringToRevers);

		// then
		assertThat(emptyString).isEmpty();
	}

	@Test
	public void shouldRepeatString() {
		// given
		String pattern = "abc";
		int numberOfRepeats = 3;

		// when
		String repeatedString = Strings.repeat(pattern, numberOfRepeats);

		// then
		assertThat(repeatedString).isEqualTo("abcabcabc");
	}

	@Test
	public void shouldRepeatStringWhenNumberOfRepeatsIsNegative() {
		// given
		String pattern = "abc";
		int numberOfRepeats = -3;

		// when
		String repeatedString = Strings.repeat(pattern, numberOfRepeats);

		// then
		assertThat(repeatedString).isEqualTo("abc");
	}

	@Test
	public void shouldRepeatNullString() {
		// given
		String pattern = null;
		int numberOfRepeats = 3;

		// when
		String repeatedString = Strings.repeat(pattern, numberOfRepeats);

		// then
		assertThat(repeatedString).isEqualTo("nullnullnull");
	}

	@Test
	public void shouldRepeatEmptyString() {
		// given
		String pattern = "";
		int numberOfRepeats = 3;

		// when
		String repeatedString = Strings.repeat(pattern, numberOfRepeats);

		// then
		assertThat(repeatedString).isEqualTo("");
	}

	@Test
	public void shouldCheckIfStringIsAlpha() {
		// given
		String value = "asasdłućś";

		// when
		boolean alpha = Strings.isAlpha(value);

		// then
		assertThat(alpha).isTrue();
	}

	@Test
	public void shouldCheckIfStringIsAlphaAndReturnFalse() {
		// given
		String value = "asasdłućś ";

		// when
		boolean alpha = Strings.isAlpha(value);

		// then
		assertThat(alpha).isFalse();
	}

	@Test
	public void shouldCheckIfStringIsAlphaWithWhitespace() {
		// given
		String value = "asasdłućś \n";

		// when
		boolean alpha = Strings.isAlphaWithWhitespace(value);

		// then
		assertThat(alpha).isTrue();
	}

	@Test
	public void shouldCheckIfStringIsNumeric() {
		// given
		String value = "1324234";

		// when
		boolean numeric = Strings.isNumeric(value);

		// then
		assertThat(numeric).isTrue();
	}

	@Test
	public void shouldCheckIfStringIsNumericAndReturnFalse() {
		// given
		String value = "1324 234 ";

		// when
		boolean numeric = Strings.isNumeric(value);

		// then
		assertThat(numeric).isFalse();
	}

	@Test
	public void shouldCheckIfStringIsAlphaNumeric() {
		// given
		String value = "1324sad234asdasd";

		// when
		boolean alphanumeric = Strings.isAlphanumeric(value);

		// then
		assertThat(alphanumeric).isTrue();
	}

	@Test
	public void shouldCheckIfStringIsAlphaNumericAndReturnFalse() {
		// given
		String value = "1324sad2 34asd asd";

		// when
		boolean alphanumeric = Strings.isAlphanumeric(value);

		// then
		assertThat(alphanumeric).isFalse();
	}

	@Test
	public void shouldCheckIfStringIsAlphaNumericWithWhitespace() {
		// given
		String value = "1324sad 234asda sd";

		// when
		boolean alphanumeric = Strings.isAlphanumericWithWhitespace(value);

		// then
		assertThat(alphanumeric).isTrue();
	}
}