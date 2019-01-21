package com.shoptasticle;

import com.shoptasticle.amazon.mws.client.AmazonPriceCheckerException;
import com.shoptasticle.domain.Price;
import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.ProductWithPrices;
import com.shoptasticle.service.PriceFinderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class GoGoGo {

	private static final Logger logger = LogManager.getLogger(GoGoGo.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private final PriceFinderService priceFinderService;

	public GoGoGo(PriceFinderService priceFinderService) {
		this.priceFinderService = priceFinderService;
	}

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
		Product product = new Product();
		product.setName("Eternal Masters Booster Box");
		product.setUrls(Arrays.asList("B01CCLTJFQ"));
		product.setTargetPrice(new BigDecimal("240"));


		ProductWithPrices productWithPrices = priceFinderService.findPrices(product);

		if (productWithPrices.getGoodPrices().isEmpty()) {
			logger.debug("Sad day, {} prices but no triggers. Cheapest is $[{}]....", productWithPrices.getPrices().size(), productWithPrices.getPrices().get(0));
		} else {
			Price bestPrice = productWithPrices.getGoodPrices().get(0);
			logger.info("OMG FREAK OUTTTTTT!!!! Go buy {} for $[{}]", bestPrice.getUrl(), bestPrice.getTotal());
		}
	}
	
}
