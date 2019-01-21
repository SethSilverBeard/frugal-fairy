package com.shoptasticle.pricefinder.integration;

import com.shoptasticle.domain.Price;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
import com.shoptasticle.pricefinder.WebPriceFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebPriceFinderTest {

    @MockBean
    AmazonPriceFinder amazonPriceFinder;

    @Autowired
    WebPriceFinder backCountry;

    @Test
    public void testLobsterGlovesGetPrice() {
        List<Price> p = backCountry.findPrices("https://www.amazon.com/Pearl-Izumi-14341508021-Lobster-Gloves/dp/B06WLNT9MF/ref=sr_1_1?ie=UTF8&qid=1548104570&sr=8-1&keywords=pearl%2Bizumi%2Blobster%2Bgloves&th=1&psc=1");
        System.out.println("p = " + p);
    }
}
