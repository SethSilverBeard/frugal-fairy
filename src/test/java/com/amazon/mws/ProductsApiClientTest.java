package com.amazon.mws;

import org.junit.Before;
import org.junit.Test;

public class ProductsApiClientTest {

	private ProductsApiClient client;
	
	@Before
	public void setupProductsApiClientTest() throws Exception {
		client = ProductsApiClient.createProductsApiClient(Credentials.createUsingConfigFile());
		
	}
	
	@Test
	public void testCallingAmazonMws() throws Exception {
		client.findOffers("B01CCLTJFQ");
	}
}
