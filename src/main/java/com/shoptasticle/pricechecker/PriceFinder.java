package com.shoptasticle.pricechecker;

import java.util.List;

public interface PriceFinder {
	/**
	 * Retrieve prices available from least to most expensive
	 */
	public List<Price> findListings(SearchCriteria searchCriteria);
	
}
