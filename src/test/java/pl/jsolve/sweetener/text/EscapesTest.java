package pl.jsolve.sweetener.text;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Maps;

public class EscapesTest {

    @Test
    public void shouldEscapeRegexp() {
        // given
        String regexp = ".()[]{}";

        // when
        String escapedValue = Escapes.escapeRegexp(regexp);

        // then
        assertThat(escapedValue).isEqualTo("\\.\\(\\)\\[\\]\\{\\}");
    }

    @Test
    public void shouldEscapeDotsInRegexp() {
        // given
        String regexp = "Ala has a cat. The cat's name is Tom.";

        // when
        String escapedValue = Escapes.escapeRegexp(regexp);

        // then
        assertThat(escapedValue).isEqualTo("Ala has a cat\\. The cat's name is Tom\\.");
    }

    @Test
    public void shouldEscapeNewLines() {
        // given
        String regexp = "First line\nSecond line\nThird line\n";
        Map<Character, String> escape = Maps.newHashMap();
        escape.put('\n', "<br />");

        // when
        String escapedValue = Escapes.escape(regexp, escape);

        // then
        assertThat(escapedValue).isEqualTo("First line<br />Second line<br />Third line<br />");
    }

    @Test
    public void shouldEscapeHtml() {
        // given
        String regexp = "First line<br />";

        // when
        String escapedValue = Escapes.escapeHtml(regexp);

        // then
        assertThat(escapedValue).isEqualTo("First line&lt;br &#047;&gt;");
    }

    @Test
    public void shouldEscapeUrl() {
        // given
        String regexp = "http://www.jsolve.pl/about us";

        // when
        String escapedValue = Escapes.escapeUrl(regexp);

        // then
        assertThat(escapedValue).isEqualTo("http://www.jsolve.pl/about%20us");
    }

    @Test
    public void shouldEscapeXml() {
        // given
        String regexp = "<root><element id='1' /></root>";

        // when
        String escapedValue = Escapes.escapeXml(regexp);

        // then
        assertThat(escapedValue).isEqualTo("&lt;root&gt;&lt;element id=&#039;1&#039; /&gt;&lt;/root&gt;");
    }

    @Test
    public void shouldEscapeJson() {
        // given
        String regexp = "{\"id\" : 1, \"name\" : \"jsolve\"}";

        // when
        String escapedValue = Escapes.escapeJson(regexp);

        // then
        assertThat(escapedValue).isEqualTo("{\\\"id\\\" : 1, \\\"name\\\" : \\\"jsolve\\\"}");
    }
}
