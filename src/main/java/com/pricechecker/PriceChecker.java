package com.pricechecker;

import java.util.List;

public interface PriceChecker {
	/**
	 * Retrieve lowest prices available from least to most expensive
	 */
	public List<Price> getLowestPrices();
}
