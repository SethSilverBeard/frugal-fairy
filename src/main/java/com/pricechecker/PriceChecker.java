package com.pricechecker;

import java.util.List;

public interface PriceChecker {
	/**
	 * Retrieve prices available from least to most expensive
	 */
	public List<Price> findPrices(SearchCriteria searchCriteria);
	
}
