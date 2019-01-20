package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.Price;
import com.shoptasticle.domain.SearchCriteria;

import java.util.List;

public interface PriceFinder {
	/**
	 * Retrieve prices available from least to most expensive
	 */
	public List<Price> findPrices(SearchCriteria searchCriteria);
	
}
