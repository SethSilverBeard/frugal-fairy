package com.listingchecker;

import java.util.List;

public interface ListingFinder {
	/**
	 * Retrieve prices available from least to most expensive
	 */
	public List<Listing> findListings(SearchCriteria searchCriteria);
	
}
