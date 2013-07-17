package pl.jsolve.sweetener.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		return join(sequence, collection.toArray());
	}

	public static String join(String sequence, Object... args) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			stringBuffer.append(args[i].toString());
			if (i != args.length - 1) {
				stringBuffer.append(sequence);
			}
		}
		return stringBuffer.toString();
	}

	public static int numberOfCccurrences(String sourceObject, String sequence) {
		Pattern pattern = Pattern.compile(sequence);
		Matcher matcher = pattern.matcher(sourceObject);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	public static int numberOfCccurrences(String sourceObject, Character sequence) {
		return numberOfCccurrences(sourceObject, sequence.toString());
	}

	public static String removeAllOccurences(String sourceObject, String sequence) {
		return sourceObject.replaceAll(sequence, "");
	}

	public static String removeAllOccurences(String sourceObject, Character sequence) {
		return removeAllOccurences(sourceObject, sequence.toString());
	}

	public static List<Integer> indexesOf(String sourceObject, String sequence) {
		List<Integer> indexes = new ArrayList<Integer>();
		Pattern pattern = Pattern.compile(sequence);
		Matcher matcher = pattern.matcher(sourceObject);
		while (matcher.find()) {
			indexes.add(matcher.start());
		}
		return indexes;
	}

	public static List<Integer> indexesOf(String sourceObject, Character c) {
		return indexesOf(sourceObject, c.toString());
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

	public static String random(int length) {
		StringBuffer sb = new StringBuffer();
		Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}

	public static String random(List<Character> symbols, int length) {
		StringBuffer sb = new StringBuffer();
		Collections.shuffle(symbols);
		for (int i = 0; i < length; i++) {
			sb.append(symbols.get(random.nextInt(symbols.size())));
		}
		return sb.toString();
	}
}
