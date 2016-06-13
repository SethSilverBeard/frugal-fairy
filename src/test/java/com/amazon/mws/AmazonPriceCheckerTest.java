package com.amazon.mws;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazon.mws.client.AmazonPriceChecker;
import com.amazon.mws.client.Credentials;
import com.pricechecker.Price;
import com.pricechecker.util.FileUtil;

public class AmazonPriceCheckerTest {
	
	private AmazonPriceChecker client;
	
	private static final String ETERNAL_MASTERS_BOOSTER_BOX_ASIN = "B01CCLTJFQ";
	
	@Before
	public void setupAmazonPriceChecker() throws Exception {
		client = AmazonPriceChecker.createProductsApiClient(Credentials.createUsingConfigFile());
	}
	
	@Test
	public void testCallingAmazonMws() throws Exception {
		client.findOffersAsString(ETERNAL_MASTERS_BOOSTER_BOX_ASIN);
	}
	
	@Test
	public void testParsingXmlFromProductsApi() throws Exception {
		String xml = FileUtil.readFileFromClasspath("amazonSampleOffers.xml");
		List<Price> prices = client.parseAmazonOffersXml(xml);
		Assert.assertThat(5, CoreMatchers.is(prices.size()));
	}
}
