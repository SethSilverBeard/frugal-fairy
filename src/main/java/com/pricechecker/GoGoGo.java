package com.pricechecker;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.mws.client.AmazonPriceChecker;
import com.amazon.mws.client.AmazonPriceCheckerException;
import com.amazon.mws.client.Credentials;

public class GoGoGo {
	
	private static final Logger logger = LogManager.getLogger(GoGoGo.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public void go() throws AmazonPriceCheckerException {
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				findEternalMastersBoxes();
			}
			
		}, 0, 30, TimeUnit.SECONDS);
	}

	public void findEternalMastersBoxes() {
		ListingFinder amazonChecker = AmazonPriceChecker.createProductsApiClient(Credentials.createUsingConfigFile());
		
		List<Listing> listings = amazonChecker.findListings(SearchCriteria.createStringSearchCriteria("B01CCLTJFQ"));  //Eternal Masters ASIN
		NotificationTrigger priceTrigger = new LowPriceTrigger(new BigDecimal("240"));
		
		for (Listing listing : listings) {
			if (priceTrigger.shouldTrigger(listing)) {
				logger.info("OMG FREAK OUTTTTTT!!!! Go buy {} for $[{}]", listing.getHttpLink(), listing.getTotal());
			} else {
				logger.debug("Sad day, no trigger for Amazon's $[{}]....",listing.getTotal());
			}
		}
	}
	
}
