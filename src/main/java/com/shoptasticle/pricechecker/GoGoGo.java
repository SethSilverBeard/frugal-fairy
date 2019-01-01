package com.shoptasticle.pricechecker;

import com.shoptasticle.amazon.mws.client.AmazonPriceChecker;
import com.shoptasticle.amazon.mws.client.AmazonPriceCheckerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GoGoGo {

	private static final Logger logger = LogManager.getLogger(GoGoGo.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@Autowired
	AmazonPriceChecker amazonChecker;
	
	public void go() throws AmazonPriceCheckerException {
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					findEternalMastersBoxes();
				} catch (Throwable e) { //need this or any exceptions (like AmazonPriceCheckerException) get swallowed.
					//see http://stackoverflow.com/a/24902026
					logger.error(e);
				}
			}
			
		}, 0, 30, TimeUnit.SECONDS);
	}

	public void findEternalMastersBoxes() {
		List<Listing> listings = amazonChecker.findListings(SearchCriteria.createStringSearchCriteria("B01CCLTJFQ"));  //Eternal Masters ASIN
		NotificationTrigger priceTrigger = new LowPriceTrigger(new BigDecimal("240"));
		
		for (Listing listing : listings) {
			if (priceTrigger.shouldTrigger(listing)) {
				logger.info("OMG FREAK OUTTTTTT!!!! Go buy {} for $[{}]", listing.getHttpLink(), listing.getTotal());
			} else {
				logger.debug("Sad day, no trigger for Amazon's $[{}]....",listing.getTotal());
				//throw new AmazonPriceCheckerException("Harrro");
			}
		}
	}
	
}
