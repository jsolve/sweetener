package pl.jsolve.sweetener.text;

import java.util.HashMap;
import java.util.Map;

import pl.jsolve.sweetener.collection.Maps;

public class Escapes {

	private static final Map<Character, String> regexpSpecial = new HashMap<>();
	private static final Map<Character, String> htmlSpecial = new HashMap<>();
	private static final Map<Character, String> urlSpecial = new HashMap<>();
	private static final Map<Character, String> xmlSpecial = new HashMap<>();
	private static final Map<Character, String> jsonSpecial = new HashMap<>();

	static {
		regexpSpecial();
		htmlSpecial();
		urlSpecial();
		xmlSpecial();
		jsonSpecial();
	}

	private static void regexpSpecial() {
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

	private static void htmlSpecial() {
		htmlSpecial.put('<', "&lt;");
		htmlSpecial.put('>', "&gt;");
		htmlSpecial.put('&', "&amp;");
		htmlSpecial.put('\"', "&quot;");
		htmlSpecial.put('\t', "&#009;");
		htmlSpecial.put('!', "&#033;");
		htmlSpecial.put('#', "&#035;");
		htmlSpecial.put('$', "&#036;");
		htmlSpecial.put('%', "&#037;");
		htmlSpecial.put('\'', "&#039;");
		htmlSpecial.put('(', "&#040;");
		htmlSpecial.put(')', "&#041;");
		htmlSpecial.put('*', "&#042;");
		htmlSpecial.put('+', "&#043;");
		htmlSpecial.put(',', "&#044;");
		htmlSpecial.put('-', "&#045;");
		htmlSpecial.put('.', "&#046;");
		htmlSpecial.put('/', "&#047;");
		htmlSpecial.put(':', "&#058;");
		htmlSpecial.put(';', "&#059;");
		htmlSpecial.put('=', "&#061;");
		htmlSpecial.put('?', "&#063;");
		htmlSpecial.put('@', "&#064;");
		htmlSpecial.put('[', "&#091;");
		htmlSpecial.put('\\', "&#092;");
		htmlSpecial.put(']', "&#093;");
		htmlSpecial.put('^', "&#094;");
		htmlSpecial.put('_', "&#095;");
		htmlSpecial.put('`', "&#096;");
		htmlSpecial.put('{', "&#123;");
		htmlSpecial.put('|', "&#124;");
		htmlSpecial.put('}', "&#125;");
		htmlSpecial.put('~', "&#126;");
	}

	private static void urlSpecial() {
		urlSpecial.put(' ', "%20");
		urlSpecial.put('"', "%22");
		urlSpecial.put('<', "%3c");
		urlSpecial.put('>', "%3e");
		urlSpecial.put('#', "%23");
		urlSpecial.put('%', "%25");
		urlSpecial.put('{', "%7b");
		urlSpecial.put('}', "%7d");
		urlSpecial.put('|', "%7c");
		urlSpecial.put('\\', "%5c");
		urlSpecial.put('^', "%5e");
		urlSpecial.put('~', "%7e");
		urlSpecial.put('[', "%5b");
		urlSpecial.put(']', "%5d");
		urlSpecial.put('`', "%60");
	}

	private static void xmlSpecial() {
		xmlSpecial.put('<', "&lt;");
		xmlSpecial.put('>', "&gt;");
		xmlSpecial.put('\"', "&quot;");
		xmlSpecial.put('\'', "&#039;");
		xmlSpecial.put('&', "&amp;");
	}

	private static void jsonSpecial() {
		jsonSpecial.put('\"', "\\\"");
		jsonSpecial.put('\\', "\\\\");
		jsonSpecial.put('/', "\\/");
		jsonSpecial.put('\b', "\\b");
		jsonSpecial.put('\f', "\\f");
		jsonSpecial.put('\n', "\\n");
		jsonSpecial.put('\r', "\\r");
		jsonSpecial.put('\t', "\\t");
	}

	public static String escapeRegexp(String value) {
		return escape(value, regexpSpecial);
	}

	public static String escapeHtml(String value) {
		return escape(value, htmlSpecial);
	}

	public static String escapeUrl(String value) {
		return escape(value, urlSpecial);
	}

	public static String escapeXml(String value) {
		return escape(value, xmlSpecial);
	}
	
	public static String escapeJson(String value) {
		return escape(value, jsonSpecial);
	}


	public static String escape(String value, Map<Character, String> specials) {
		StringBuffer sb = new StringBuffer(value);
		int countOfReplacements = 0;
		for (int i = 0; i < value.length(); i++) {
			if (specials.containsKey(value.charAt(i))) {
				sb.deleteCharAt(i + countOfReplacements).insert(i + countOfReplacements, specials.get(value.charAt(i)));
				countOfReplacements += specials.get(value.charAt(i)).length() - 1;
			}
		}
		return sb.toString();
	}

	public static Map<Character, String> getRegexpspecial() {
		return Maps.newHashMap(regexpSpecial);
	}

	public static Map<Character, String> getHtmlspecial() {
		return Maps.newHashMap(htmlSpecial);
	}

	public static Map<Character, String> getUrlspecial() {
		return Maps.newHashMap(urlSpecial);
	}

	public static Map<Character, String> getXmlspecial() {
		return Maps.newHashMap(xmlSpecial);
	}

	public static Map<Character, String> getJsonspecial() {
		return Maps.newHashMap(jsonSpecial);
	}

}