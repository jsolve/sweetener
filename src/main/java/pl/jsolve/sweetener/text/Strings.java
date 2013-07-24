package pl.jsolve.sweetener.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.jsolve.sweetener.core.FoundGroup;
import pl.jsolve.sweetener.exception.InvalidArgumentException;

public class Strings {

	private static final char CARRIAGE_RETURN = '\r';
	private static final char TAB = '\t';
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	private static final String EMPTY_STRING = "";
	public static final String DOT = "/.";

	private static final List<Character> symbols = new ArrayList<Character>(62);
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
			return new ArrayList<Integer>();
		}
		if (ignoreRegexp) {
			return indexesOf(sourceObject, Escapes.escapeRegexp(sequence));
		}
		return indexesOf(sourceObject, sequence);
	}

	public static List<Integer> indexesOf(String sourceObject, String sequence) {
		List<Integer> indexes = new ArrayList<Integer>();
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
			return new ArrayList<Integer>();
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
			return new ArrayList<FoundGroup>();
		}
		if (ignoreRegexp) {
			return groups(sourceObject, Escapes.escapeRegexp(sequence));
		}
		return groups(sourceObject, sequence);
	}

	public static List<FoundGroup> groups(String sourceObject, String sequence) {
		List<FoundGroup> indexes = new ArrayList<FoundGroup>();
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
		Collections.shuffle(symbols);
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
		Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}

	public static String pad(String sourceObject, int length) {
		return pad(sourceObject, SPACE, length);
	}

	public static String pad(String sourceObject, Character c, int length) {
		if (c == null) {
			return pad(sourceObject, SPACE, length);
		}
		return pad(sourceObject, c.toString(), length);
	}

	public static String pad(String sourceObject, String content, int length) {
		if (content == null || content.isEmpty()) {
			throw new InvalidArgumentException("Content cannot be empty");
		}
		sourceObject = defaultIfNull(sourceObject, EMPTY_STRING);
		if (sourceObject.length() >= length) {
			return sourceObject;
		}
		StringBuilder sb = new StringBuilder(sourceObject);
		for (int i = 0; i < length - sourceObject.length(); i++) {
			sb.append(content.charAt(i % content.length()));
		}
		return sb.toString();
	}

	public static String defaultIfNull(String value, String defaultValue) {
		return value != null ? value : defaultValue;
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
}
