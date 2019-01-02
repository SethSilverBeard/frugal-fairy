package com.shoptasticle.pricechecker;

import java.math.BigDecimal;
import java.util.Objects;

public class LowPriceTrigger implements NotificationTrigger {

	private final BigDecimal price;
	
	/**
	 * Create a trigger which fires when price reaches {@code lowPrice} or below
	 * 
	 * @param lowPrice MwsPrice at which to trigger notifications (Offer price must be <= lowPrice to trigger)
	 */
	public LowPriceTrigger(final BigDecimal lowPrice) {
		Objects.requireNonNull(lowPrice);
		this.price = lowPrice;
	}
	
	@Override
	public boolean shouldTrigger(Price listing) {
		if (Objects.isNull(listing.getTotal()) || listing.getTotal().compareTo(price) > 0) {
			return false;
		}
		return true;
	}
}
