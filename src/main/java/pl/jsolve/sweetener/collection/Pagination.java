package pl.jsolve.sweetener.collection;

import java.util.Collection;

public class Pagination<T> {

	private final int page;
	private final int resultsPerPage;
	private final int totalElements;
	private final int numberOfPages;
	private final Collection<T> elementsOfPage;

	public Pagination(int page, int resultsPerPage, int totalElements, Collection<T> elementsOfPage) {
		this.page = page;
		this.resultsPerPage = resultsPerPage;
		this.totalElements = totalElements;
		this.numberOfPages = calculateNumberOfPages(resultsPerPage, totalElements);
		this.elementsOfPage = elementsOfPage;
	}

	private int calculateNumberOfPages(int resultsPerPage, int totalElements) {
		return (totalElements + resultsPerPage - 1) / resultsPerPage;
	}

	public int getPage() {
		return page;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public Collection<T> getElementsOfPage() {
		return elementsOfPage;
	}

}
