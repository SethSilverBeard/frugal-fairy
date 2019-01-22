package com.shoptasticle.pricefinder.integration;

import com.shoptasticle.amazon.mws.client.Credentials;
import com.shoptasticle.domain.Price;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
import com.shoptasticle.pricefinder.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(classes= {
		AmazonPriceFinder.class
})
@EnableConfigurationProperties(Credentials.class)
@RunWith(SpringRunner.class)
public class AmazonMwsPriceCheckerTest {

	private static final String ETERNAL_MASTERS_BOOSTER_BOX_ASIN = "B01CCLTJFQ";

	@Autowired
	private AmazonPriceFinder client;

	@Test
	public void testCallingAmazonMws() throws Exception {
		client.findOffersAsString(ETERNAL_MASTERS_BOOSTER_BOX_ASIN);
	}

	@Test
	public void testParsingXmlFromProductsApi() throws Exception {
		String xml = FileUtil.readFileFromClasspath("amazonSampleOffers.xml");
		List<Price> prices = client.parseAmazonOffersXml(xml);
		Price expectedFirstPrice = new Price();
		expectedFirstPrice.setTotal(new BigDecimal("287.99"));
		expectedFirstPrice.setShippingPrice(new BigDecimal("0.00"));
		expectedFirstPrice.setItemPrice(new BigDecimal("287.99"));
		expectedFirstPrice.setUrl("https://www.amazon.com/dp/B01CCLTJFQ");

		Assert.assertEquals(5, prices.size());
		Price actualFirstPrice = prices.get(0);
		Assert.assertEquals(new BigDecimal("287.99"), actualFirstPrice.getItemPrice());
		Assert.assertEquals(new BigDecimal("0.00"), actualFirstPrice.getShippingPrice());
		Assert.assertEquals(new BigDecimal("287.99"), actualFirstPrice.getTotal());
		Assert.assertEquals("https://www.amazon.com/dp/B01CCLTJFQ", actualFirstPrice.getUrl());
		Assert.assertNotNull(actualFirstPrice.getDateRetrieved());
		//validate remaining total prices
		Assert.assertEquals(new BigDecimal("295.00"), prices.get(1).getTotal());
		Assert.assertEquals(new BigDecimal("301.09"), prices.get(2).getTotal());
		Assert.assertEquals(new BigDecimal("301.10"), prices.get(3).getTotal());
		Assert.assertEquals(new BigDecimal("304.48"), prices.get(4).getTotal());
	}
}
