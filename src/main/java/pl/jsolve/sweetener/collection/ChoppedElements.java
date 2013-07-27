package pl.jsolve.sweetener.collection;

import java.util.Collection;
import java.util.List;

import pl.jsolve.sweetener.exception.PaginationException;

public class ChoppedElements<T> {

	private int page;
	private final int resultsPerPage;
	private final int totalElements;
	private final int numberOfPages;
	private final List<Collection<T>> listOfPages;

	public ChoppedElements(int page, int resultsPerPage, int totalElements, List<Collection<T>> listOfPages) {
		this.page = page;
		this.resultsPerPage = resultsPerPage;
		this.totalElements = totalElements;
		this.numberOfPages = calculateNumberOfPages(resultsPerPage, totalElements);
		this.listOfPages = listOfPages;
	}

	private int calculateNumberOfPages(int resultsPerPage, int totalElements) {
		return (totalElements + resultsPerPage - 1) / resultsPerPage;
	}

	public boolean hasNextPage() {
		return page + 1 <= numberOfPages;
	}

	public void nextPage() {
		if (page + 1 > numberOfPages) {
			throw new PaginationException("The next page does not exist");
		}
		this.page = page + 1;
	}
	
	public boolean hasPreviousPage() {
		return page - 1 >= 0;
	}

	public void previousPage() {
		if (page - 1 < 0) {
			throw new PaginationException("The previous page does not exist");
		}
		this.page = page - 1;
	}
	
	public Collection<T> getElementsOfPage() {
		return listOfPages.get(page);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public List<Collection<T>> getListOfPages() {
		return listOfPages;
	}

}
