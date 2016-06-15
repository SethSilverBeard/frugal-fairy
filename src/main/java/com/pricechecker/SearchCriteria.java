package com.pricechecker;

import java.util.Objects;

/**
 * Simple wrapper for searching by string.  Storing in a SearchCriteria wrapper object so it's easy to expand later when we want to search by key/value pairs.
 * @author wingert
 *
 */
public class SearchCriteria {
	
	private String searchString;
	
	private SearchCriteria(String searchString) {
		Objects.requireNonNull(searchString);
		this.searchString = searchString;
	}

	public String getSearchString() {
		return searchString;
	}
	
	public static SearchCriteria createStringSearchCriteria(String searchString) {
		return new SearchCriteria(searchString);
	}
	
}
