package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.Price;

import java.util.List;

public interface PriceFinder {
	/**
	 * Retrieve prices available from least to most expensive
	 */
	List<Price> findPrices(String url);
	
}
