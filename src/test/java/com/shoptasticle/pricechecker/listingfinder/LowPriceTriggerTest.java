package com.shoptasticle.pricechecker.listingfinder;

import com.shoptasticle.pricechecker.Price;
import com.shoptasticle.pricechecker.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class LowPriceTriggerTest {

	private final BigDecimal TARGET_PRICE = new BigDecimal("240.00");
	private final BigDecimal EXPENSIVE_PRICE = new BigDecimal("240.01");
	private final BigDecimal CHEAP_PRICE = new BigDecimal("239.99");

	@Test
	public void testPricesSet() {
		Set<Price> prices = new TreeSet<>();
		prices.add(new Price(CHEAP_PRICE));
		prices.add(new Price(CHEAP_PRICE.subtract(new BigDecimal("5.00"))));
		assertThat(prices).hasSize(2);
//
//		Set<BigDecimal> moneys = new TreeSet<>();
//		moneys.add(CHEAP_PRICE);
//		moneys.add(CHEAP_PRICE.subtract(new BigDecimal("5.00")));
//		assertThat(moneys).hasSize(2);
	}

	@Test
	public void testCheapPriceTriggers() throws Exception {
		Product product = new Product();
		product.setTargetPrice(TARGET_PRICE);
		Price cheapPrice = new Price(CHEAP_PRICE);
		product.setPrices(Arrays.asList(cheapPrice));
		assertThat(product.getGoodPrices()).hasSize(1);

		Price cheaperListing = new Price();
		cheaperListing.setTotal(CHEAP_PRICE.subtract(new BigDecimal("5.00")));
		product.addPrice(cheaperListing);
		assertThat(product.getGoodPrices()).hasSize(2);

		//Ensure they're sorted cheapest to
		assertThat(product.getGoodPrices().get(0).getTotal()).isEqualByComparingTo(CHEAP_PRICE.subtract(new BigDecimal("5.00")));
	}

	@Test
	public void testSortedPrices() {
		Product product = new Product();
		Price cheapListing = new Price();
		cheapListing.setTotal(CHEAP_PRICE);
		Price cheaperListing = new Price();
		cheaperListing.setTotal(CHEAP_PRICE.subtract(new BigDecimal("5.00")));
		product.setPrices(Arrays.asList(cheapListing, cheaperListing));

		assertThat(product.getPrices().get(0)).isEqualTo(cheaperListing);
		assertThat(product.getPrices().get(1)).isEqualTo(cheapListing);

		Price cheapestPrice = new Price(CHEAP_PRICE.subtract(new BigDecimal("10.00")));
		product.addPrice(cheapestPrice);
		assertThat(product.getPrices().get(0)).isEqualTo(cheapestPrice);

	}
	
	@Test
	public void testExpensivePriceDoesNotTrigger() throws Exception {
		Product product = new Product();
		product.setTargetPrice(TARGET_PRICE);
		Price expensiveListing = new Price();
		expensiveListing.setTotal(EXPENSIVE_PRICE);
		product.setPrices(Arrays.asList(expensiveListing));
		assertThat(product.getGoodPrices()).isEmpty();
	}
}
