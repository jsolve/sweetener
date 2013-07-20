package pl.jsolve.sweetener.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.jsolve.sweetener.core.FoundGroup;

public class Strings {

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
			return "";
		}
		return join(sequence, collection.toArray());
	}

	public static String join(String sequence, Object... args) {
		if (args == null) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				stringBuffer.append("null");
			} else {
				stringBuffer.append(args[i].toString());
			}
			if (i != args.length - 1) {
				stringBuffer.append(sequence);
			}
		}
		return stringBuffer.toString();
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
		if (sourceObject == null) {
			return sourceObject;
		}
		if (sequence == null) {
			return sourceObject;
		}
		if (ignoreRegexp) {
			return sourceObject.replaceAll(Escapes.escapeRegexp(sequence), "");
		}
		return sourceObject.replaceAll(sequence, "");
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
			return new ArrayList<FoundGroup>();
		}
		if (ignoreRegexp) {
			return groups(sourceObject, Escapes.escapeRegexp(c.toString()));
		}
		return groups(sourceObject, c.toString());
	}

	public static String random(int length) {
		StringBuffer sb = new StringBuffer();
		Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}

	public static String random(List<Character> symbols, int length) {
		if (symbols == null || symbols.isEmpty()) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}

	public static String pad(String sourceObject, int length) {
		return pad(sourceObject, " ", length);
	}

	public static String pad(String sourceObject, Character c, int length) {
		if (c == null) {
			return pad(sourceObject, " ", length);
		}
		return pad(sourceObject, c.toString(), length);
	}

	public static String pad(String sourceObject, String content, int length) {
		if (content == null || content.isEmpty()) {
			throw new RuntimeException();
		}
		sourceObject = defaultIfNull(sourceObject, "");
		if (sourceObject.length() >= length) {
			return sourceObject;
		}
		StringBuffer sb = new StringBuffer(sourceObject);
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
		StringBuffer sb = new StringBuffer(value);
		sb.deleteCharAt(0).insert(0, ("" + value.charAt(0)).toUpperCase());

		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == ' ' || value.charAt(i) == '\n' || value.charAt(i) == '\t' || value.charAt(i) == '\r') {
				whitespace = true;
				continue;
			}
			if (whitespace) {
				sb.deleteCharAt(i).insert(i, ("" + value.charAt(i)).toUpperCase());
				whitespace = false;
			}
		}
		return sb.toString();
	}
}
