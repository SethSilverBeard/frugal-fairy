package com.amazon.mws;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amazon.mws.client.AmazonPriceChecker;
import com.amazon.mws.client.Credentials;
import com.pricechecker.Price;

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
		String xml = client.findOffersAsString(ETERNAL_MASTERS_BOOSTER_BOX_ASIN);
		List<Price> prices = client.parseAmazonOffersXml(xml);
	}
}
