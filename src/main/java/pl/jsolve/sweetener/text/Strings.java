package pl.jsolve.sweetener.text;

import static pl.jsolve.sweetener.core.Objects.nullSafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.core.FoundGroup;
import pl.jsolve.sweetener.core.OnNullStrategy;
import pl.jsolve.sweetener.exception.InvalidArgumentException;

public class Strings {

	private static final PaddingType DEFAULT_PADDING = PaddingType.RIGHT;
	private static final Character CARRIAGE_RETURN = '\r';
	private static final Character TAB = '\t';
	private static final Character NEW_LINE = '\n';
	private static final Character SPACE = ' ';
	private static final String EMPTY_STRING = "";
	public static final String DOT = "/.";

	private static final List<Character> symbols = new ArrayList<>(62);
	private static final Random random = new Random();

	static {
		for (int idx = 0; idx < 10; ++idx) {
			symbols.add((char) ('0' + idx));
		}
		for (int idx = 10; idx < 36; ++idx) {
			symbols.add((char) ('a' + idx - 10));
		}
		for (int idx = 36; idx < 62; ++idx) {
			symbols.add((char) ('A' + idx - 36));
		}
	}

	public static String join(String sequence, Collection<?> collection) {
		if (collection == null) {
			return EMPTY_STRING;
		}
		return join(sequence, collection.toArray());
	}

	public static String join(String sequence, Object... args) {
		if (args == null) {
			return EMPTY_STRING;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			stringBuilder.append(args[i]);
			if (i != args.length - 1) {
				stringBuilder.append(sequence);
			}
		}
		return stringBuilder.toString();
	}

	public static int numberOfOccurrences(String sourceObject, String sequence) {
		if (sourceObject == null || sequence == null || sequence.isEmpty()) {
			return 0;
		}
		Pattern pattern = Pattern.compile(sequence);
		Matcher matcher = pattern.matcher(sourceObject);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	public static int numberOfOccurrences(String sourceObject, String sequence, boolean ignoreRegexp) {
		if (sequence == null) {
			return 0;
		}
		if (ignoreRegexp) {
			return numberOfOccurrences(sourceObject, Escapes.escapeRegexp(sequence));
		}
		return numberOfOccurrences(sourceObject, sequence);
	}

	public static int numberOfOccurrences(String sourceObject, Character sequence) {
		return numberOfOccurrences(sourceObject, sequence, false);
	}

	public static int numberOfOccurrences(String sourceObject, Character sequence, boolean ignoreRegexp) {
		if (sequence == null) {
			return 0;
		}
		if (ignoreRegexp) {
			return numberOfOccurrences(sourceObject, Escapes.escapeRegexp(sequence.toString()));
		}
		return numberOfOccurrences(sourceObject, sequence.toString());
	}

	public static String removeAllOccurrences(String sourceObject, String sequence) {
		return removeAllOccurrences(sourceObject, sequence, false);
	}

	public static String removeAllOccurrences(String sourceObject, String sequence, boolean ignoreRegexp) {
		if (sourceObject == null || sequence == null) {
			return sourceObject;
		}
		if (ignoreRegexp) {
			return sourceObject.replaceAll(Escapes.escapeRegexp(sequence), EMPTY_STRING);
		}
		return sourceObject.replaceAll(sequence, EMPTY_STRING);
	}

	public static String removeAllOccurrences(String sourceObject, Character sequence) {
		if (sequence == null) {
			return sourceObject;
		}
		return removeAllOccurrences(sourceObject, sequence.toString(), false);
	}

	public static String removeAllOccurrences(String sourceObject, Character sequence, boolean ignoreRegexp) {
		return removeAllOccurrences(sourceObject, sequence.toString(), ignoreRegexp);
	}

	public static List<Integer> indexesOf(String sourceObject, String sequence, boolean ignoreRegexp) {
		if (sequence == null) {
			return new ArrayList<>();
		}
		if (ignoreRegexp) {
			return indexesOf(sourceObject, Escapes.escapeRegexp(sequence));
		}
		return indexesOf(sourceObject, sequence);
	}

	public static List<Integer> indexesOf(String sourceObject, String sequence) {
		List<Integer> indexes = new ArrayList<>();
		if (sourceObject == null || sequence == null || sequence.isEmpty()) {
			return indexes;
		}
		Pattern pattern = Pattern.compile(sequence);
		Matcher matcher = pattern.matcher(sourceObject);
		while (matcher.find()) {
			indexes.add(matcher.start());
		}
		return indexes;
	}

	public static List<Integer> indexesOf(String sourceObject, Character c, boolean ignoreRegexp) {
		if (c == null) {
			return new ArrayList<>();
		}
		if (ignoreRegexp) {
			return indexesOf(sourceObject, Escapes.escapeRegexp(c.toString()));
		}
		return indexesOf(sourceObject, c.toString());
	}

	public static List<Integer> indexesOf(String sourceObject, Character c) {
		return indexesOf(sourceObject, c, false);
	}

	public static List<FoundGroup> groups(String sourceObject, String sequence, boolean ignoreRegexp) {
		if (sequence == null) {
			return new ArrayList<>();
		}
		if (ignoreRegexp) {
			return groups(sourceObject, Escapes.escapeRegexp(sequence));
		}
		return groups(sourceObject, sequence);
	}

	public static List<FoundGroup> groups(String sourceObject, String sequence) {
		List<FoundGroup> indexes = new ArrayList<>();
		Pattern pattern = Pattern.compile(sequence);
		Matcher matcher = pattern.matcher(sourceObject);
		while (matcher.find()) {
			indexes.add(new FoundGroup(matcher.start(), matcher.end(), matcher.group()));
		}
		return indexes;
	}

	public static List<FoundGroup> groups(String sourceObject, Character c) {
		return groups(sourceObject, c, false);
	}

	public static List<FoundGroup> groups(String sourceObject, Character c, boolean ignoreRegexp) {
		if (c == null) {
			return new ArrayList<>();
		}
		if (ignoreRegexp) {
			return groups(sourceObject, Escapes.escapeRegexp(c.toString()));
		}
		return groups(sourceObject, c.toString());
	}

	public static String random(int length) {
		StringBuilder sb = new StringBuilder();
		java.util.Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}

	public static String random(List<Character> symbols, int length) {
		if (symbols == null || symbols.isEmpty()) {
			return EMPTY_STRING;
		}
		StringBuilder sb = new StringBuilder();
		java.util.Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}

	public static String pad(String sourceObject, int length, PaddingType paddingType) {
		return pad(sourceObject, SPACE, length, paddingType);
	}

	public static String pad(String sourceObject, int length) {
		return pad(sourceObject, length, DEFAULT_PADDING);
	}

	public static String pad(String sourceObject, Character c, int length, PaddingType paddingType) {
		if (c == null) {
			return pad(sourceObject, SPACE, length, paddingType);
		}
		return pad(sourceObject, c.toString(), length, paddingType);
	}

	public static String pad(String sourceObject, Character c, int length) {
		return pad(sourceObject, c.toString(), length, DEFAULT_PADDING);
	}

	public static String pad(String sourceObject, String content, int length) {
		return pad(sourceObject, content, length, DEFAULT_PADDING);
	}

	public static String pad(String sourceObject, String content, int length, PaddingType paddingType) {
		throwExceptionWhenContentIsEmpty(content);
		throwExceptionWhenPaddingTypeIsNull(paddingType);

		sourceObject = getEmptyStringWhenNull(sourceObject);
		int numberOfPadding = length - sourceObject.length();
		if (numberOfPadding <= 0) {
			return sourceObject;
		}
		StringBuilder sb = null;
		switch (paddingType) {
		case CENTRE:
			sb = insertCentre(sourceObject, content, numberOfPadding, paddingType);
			break;
		case LEFT:
		case RIGHT:
			sb = insertLeftOrRight(sourceObject, content, numberOfPadding, paddingType);
			break;
		default:
			throw new InvalidArgumentException("Invalid PaddingType.");
		}
		return sb.toString();
	}

	private static void throwExceptionWhenContentIsEmpty(String content) {
		if (content == null || content.isEmpty()) {
			throw new InvalidArgumentException("Content cannot be empty");
		}
	}

	private static void throwExceptionWhenPaddingTypeIsNull(PaddingType paddingType) {
		if (paddingType == null) {
			throw new InvalidArgumentException("Padding type must be specified");
		}
	}

	private static String getEmptyStringWhenNull(String sourceObject) {
		return nullSafe(sourceObject, new OnNullStrategy<String>() {
			@Override
			public String onNull() {
				return EMPTY_STRING;
			}
		});
	}

	private static StringBuilder insertCentre(String sourceObject, String content, int numberOfPadding, PaddingType paddingType) {
		StringBuilder sb = new StringBuilder(sourceObject);
		int leftPad = numberOfPadding / 2;
		int rigthPad = numberOfPadding - leftPad;

		for (int i = 0; i < leftPad; i++) {
			char c = content.charAt(i % content.length());
			insertLeft(sb, c);
		}

		for (int i = 0; i < rigthPad; i++) {
			char c = content.charAt(i % content.length());
			insertRight(sb, c);
		}

		return sb;
	}

	private static StringBuilder insertLeftOrRight(String sourceObject, String content, int numberOfPadding, PaddingType paddingType) {
		StringBuilder sb = new StringBuilder(sourceObject);
		for (int i = 0; i < numberOfPadding; i++) {
			char c = content.charAt(i % content.length());
			switch (paddingType) {
			case LEFT:
				insertLeft(sb, c);
				break;
			case RIGHT:
				insertRight(sb, c);
				break;
			default:
				break;
			}
		}
		return sb;
	}

	private static void insertRight(StringBuilder sb, char c) {
		sb.append("" + c);
	}

	private static void insertLeft(StringBuilder sb, char c) {
		sb.insert(0, c);
	}

	public static String capitalize(String value) {
		if (value == null) {
			throw new RuntimeException();
		}
		if (value.isEmpty()) {
			return value;
		}
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	public static String capitalizeAllWords(String value) {
		if (value == null) {
			throw new RuntimeException();
		}
		if (value.isEmpty()) {
			return value;
		}
		boolean whitespace = false;
		StringBuilder sb = new StringBuilder(value);
		sb.deleteCharAt(0).insert(0, (EMPTY_STRING + value.charAt(0)).toUpperCase());

		for (int i = 0; i < value.length(); i++) {
			if (isWhitespace(value.charAt(i))) {
				whitespace = true;
				continue;
			}
			if (whitespace) {
				sb.deleteCharAt(i).insert(i, (String.valueOf(value.charAt(i)).toUpperCase()));
				whitespace = false;
			}
		}
		return sb.toString();
	}

	public static boolean isWhitespace(char c) {
		return c == SPACE || c == NEW_LINE || c == TAB || c == CARRIAGE_RETURN;
	}

	public static boolean isWhitespace(String c) {
		return c.equals(SPACE) || c.equals(NEW_LINE) || c.equals(TAB) || c.equals(CARRIAGE_RETURN);
	}

	public static boolean containsOnly(String value, List<Character> listOfCharacters) {
		if (value == null) {
			return true;
		}
		if (listOfCharacters == null) {
			throw new InvalidArgumentException("List of characters cannot be null");
		}
		for (int i = 0; i < value.length(); i++) {
			if (!listOfCharacters.contains(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(String value) {
		List<Character> whitespaces = Collections.newArrayList(SPACE, NEW_LINE, TAB, CARRIAGE_RETURN);
		if (value == null || containsOnly(value, whitespaces)) {
			return true;
		}

		return false;
	}

	public static String singleLine(String value) {
		return removeAllOccurrences(removeAllOccurrences(value, NEW_LINE), CARRIAGE_RETURN);
	}

	public static String removeNewLines(String value) {
		int i = value.length() - 1;
		if (i < 0) {
			return value;
		}
		while (true) {
			if (i < 0) {
				break;
			}
			Character character = value.charAt(i);
			if (!(NEW_LINE.equals(character) || CARRIAGE_RETURN.equals(character))) {
				break;
			}
			i--;
		}
		return value.substring(0, i + 1);
	}

	public static String reverse(String value) {
		if (value == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();

		for (int i = value.length() - 1; i >= 0; i--) {
			sb.append(value.charAt(i));
		}

		return sb.toString();
	}

	public static String repeat(String value, int numberOfRepeats) {
		if (numberOfRepeats <= 0) {
			return value;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfRepeats; i++) {
			sb.append(value);
		}
		return sb.toString();
	}

	public static boolean isAlpha(String value) {
		return value.matches("^[\\pL]+$");
	}

	public static boolean isAlphaWithWhitespace(String value) {
		return value.matches("^[\\pL\\s]+$");
	}

	public static boolean isNumeric(String value) {
		return value.matches("^[\\pN]+$");
	}

	public static boolean isAlphanumeric(String value) {
		return value.matches("^[\\pL\\pN]+$");
	}

	public static boolean isAlphanumericWithWhitespace(String value) {
		return value.matches("^[\\pL\\pN\\s]+$");
	}

}