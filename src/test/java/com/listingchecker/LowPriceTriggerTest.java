package com.listingchecker;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class LowPriceTriggerTest {

	private final BigDecimal BASELINE_PRICE = new BigDecimal("240.00");
	private final BigDecimal EXPENSIVE_PRICE = new BigDecimal("240.01");
	private final BigDecimal CHEAP_PRICE = new BigDecimal("239.99");
	
	@Test
	public void testCheapPriceTriggers() throws Exception {
		NotificationTrigger lowPriceTrigger = new LowPriceTrigger(BASELINE_PRICE);
		Listing cheapListing = new Listing();
		cheapListing.setTotal(CHEAP_PRICE);
		Assert.assertTrue(lowPriceTrigger.shouldTrigger(cheapListing));
		
		Listing cheaperListing = new Listing();
		cheaperListing.setTotal(CHEAP_PRICE.subtract(new BigDecimal("5.00")));
		Assert.assertTrue(lowPriceTrigger.shouldTrigger(cheaperListing));
	}
	
	@Test
	public void testExpensivePriceDoesNotTrigger() throws Exception {
		NotificationTrigger lowPriceTrigger = new LowPriceTrigger(BASELINE_PRICE);
		Listing expensiveListing = new Listing();
		expensiveListing.setTotal(EXPENSIVE_PRICE);
		Assert.assertFalse(lowPriceTrigger.shouldTrigger(expensiveListing));
	}
}
