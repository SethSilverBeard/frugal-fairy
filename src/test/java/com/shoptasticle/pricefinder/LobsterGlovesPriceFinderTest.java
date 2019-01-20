package com.shoptasticle.pricefinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LobsterGlovesPriceFinderTest {

    @Test
    public void testIdealPriceFinder() {
        //TODO: this requires refactoring amazon finder to use SINGLETON for MWS clients
        //PriceFinder amazonFinder = new AmazonPriceFinder("ASIN");  //
        PriceFinder backCountryFinder = new WebPriceFinder("URL");

    }
}
