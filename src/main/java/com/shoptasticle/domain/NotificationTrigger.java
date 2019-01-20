package com.shoptasticle.domain;

import com.shoptasticle.domain.Price;

/**
 * Maybe use this instead of coupling Product with getGoodPRices() method
 */
public interface NotificationTrigger {
	//take in a price
	public boolean shouldTrigger(Price price);
}
