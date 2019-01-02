package com.shoptasticle.pricechecker;

/**
 * Maybe use this instead of coupling Product with getGoodPRices() method
 */
public interface NotificationTrigger {
	//take in a price
	public boolean shouldTrigger(Price price);
}
