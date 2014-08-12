package pl.jsolve.sweetener.collection;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PaginationTest {

	@Test
	public void shouldReturnPaginationObject() {
		// given
		int page = 0;
		int resultsParPage = 10;
		int totalElements = 100;

		// when
		Pagination<?> pagination = new Pagination<Object>(page, resultsParPage, totalElements, null);

		// then
		assertThat(pagination.getNumberOfPages()).isEqualTo(10);
	}

	@Test
	public void shouldReturnPaginationObject2() {
		// given
		int page = 0;
		int resultsParPage = 10;
		int totalElements = 0;

		// when
		Pagination<?> pagination = new Pagination<Object>(page, resultsParPage, totalElements, null);

		// then
		assertThat(pagination.getNumberOfPages()).isEqualTo(0);
	}

	@Test
	public void shouldReturnPaginationObject3() {
		// given
		int page = 0;
		int resultsParPage = 6;
		int totalElements = 23;

		// when
		Pagination<?> pagination = new Pagination<Object>(page, resultsParPage, totalElements, null);

		// then
		assertThat(pagination.getNumberOfPages()).isEqualTo(4);
	}


}
