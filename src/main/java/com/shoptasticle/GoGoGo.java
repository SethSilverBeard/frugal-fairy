package com.shoptasticle;

import com.shoptasticle.amazon.mws.client.AmazonPriceCheckerException;
import com.shoptasticle.domain.Price;
import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.SearchCriteria;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
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
	AmazonPriceFinder amazonChecker;
	
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
		List<Price> prices = amazonChecker.findPrices(SearchCriteria.createStringSearchCriteria("B01CCLTJFQ"));  //Eternal Masters ASIN
		Product product = new Product();
		product.setPrices(prices);
		product.setTargetPrice(new BigDecimal("240"));

		if (product.getGoodPrices().isEmpty()) {
			logger.debug("Sad day, {} prices but no triggers. Cheapest is $[{}]....", product.getPrices().size(), product.getPrices().get(0));
		} else {
			Price bestPrice = product.getGoodPrices().get(0);
			logger.info("OMG FREAK OUTTTTTT!!!! Go buy {} for $[{}]", bestPrice.getUrl(), bestPrice.getTotal());
		}
	}
	
}
