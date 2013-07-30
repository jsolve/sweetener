package pl.jsolve.sweetener.text;

import java.util.HashMap;
import java.util.Map;

public class Escapes {

	private static final Map<Character, String> regexpSpecial = new HashMap<>();

	static {
		regexpSpecial.put('.', "\\.");
		regexpSpecial.put('\\', "\\\\");
		regexpSpecial.put('?', "\\?");
		regexpSpecial.put('*', "\\*");
		regexpSpecial.put('+', "\\+");
		regexpSpecial.put('&', "\\&");
		regexpSpecial.put(':', "\\:");
		regexpSpecial.put('{', "\\{");
		regexpSpecial.put('}', "\\}");
		regexpSpecial.put('[', "\\[");
		regexpSpecial.put(']', "\\]");
		regexpSpecial.put('(', "\\(");
		regexpSpecial.put(')', "\\)");
		regexpSpecial.put('^', "\\^");
		regexpSpecial.put('$', "\\$");
	}

	public static String escapeRegexp(String value) {
		StringBuffer sb = new StringBuffer(value);
		int countOfReplacements = 0;
		for (int i = 0; i < value.length(); i++) {
			if (regexpSpecial.containsKey(value.charAt(i))) {
				sb.deleteCharAt(i + countOfReplacements).insert(i + countOfReplacements, regexpSpecial.get(value.charAt(i)));
				countOfReplacements += regexpSpecial.get(value.charAt(i)).length() - 1;
			}
		}
		return sb.toString();
	}
}