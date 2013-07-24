package pl.jsolve.sweetener.text;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class EscapesTest {

	@Test
	public void shouldEscapeRegexp() {
		// given
		String regexp = ".()[]{}";

		// when
		String escapedRegexp = Escapes.escapeRegexp(regexp);

		// then
		assertThat(escapedRegexp).isEqualTo("\\.\\(\\)\\[\\]\\{\\}");
	}

	@Test
	public void shouldEscapeDotsInRegexp() {
		// given
		String regexp = "Ala has a cat. The cat's name is Tom.";

		// when
		String escapedRegexp = Escapes.escapeRegexp(regexp);

		// then
		assertThat(escapedRegexp).isEqualTo("Ala has a cat\\. The cat's name is Tom\\.");
	}
}
