package com.shoptasticle.pricechecker;

public interface NotificationTrigger {
	//take in a price
	public boolean shouldTrigger(Listing price);
}
