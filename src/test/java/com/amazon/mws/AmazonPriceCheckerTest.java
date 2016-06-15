package com.amazon.mws;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazon.mws.client.AmazonPriceChecker;
import com.amazon.mws.client.Credentials;
import com.listingchecker.Listing;
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
		List<Listing> prices = client.parseAmazonOffersXml(xml);
		Listing expectedFirstPrice = new Listing();
		expectedFirstPrice.setTotal(new BigDecimal("287.99"));
		expectedFirstPrice.setShippingPrice(new BigDecimal("0.00"));
		expectedFirstPrice.setItemPrice(new BigDecimal("287.99"));
		expectedFirstPrice.setHttpLink("https://www.amazon.com/dp/B01CCLTJFQ");
		
		Assert.assertEquals(5, prices.size());
		Listing actualFirstPrice = prices.get(0);
		Assert.assertEquals(new BigDecimal("287.99"), actualFirstPrice.getItemPrice());
		Assert.assertEquals(new BigDecimal("0.00"), actualFirstPrice.getShippingPrice());
		Assert.assertEquals(new BigDecimal("287.99"), actualFirstPrice.getTotal());
		Assert.assertEquals("https://www.amazon.com/dp/B01CCLTJFQ", actualFirstPrice.getHttpLink());
		Assert.assertNotNull(actualFirstPrice.getDateRetrieved());
		//validate remaining total prices
		Assert.assertEquals(new BigDecimal("295.00"), prices.get(1).getTotal());
		Assert.assertEquals(new BigDecimal("301.09"), prices.get(2).getTotal());
		Assert.assertEquals(new BigDecimal("301.10"), prices.get(3).getTotal());
		Assert.assertEquals(new BigDecimal("304.48"), prices.get(4).getTotal());
	}
}
