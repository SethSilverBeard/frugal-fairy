package com.shoptasticle.pricefinder.integration;

import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.ProductWithPrices;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
import com.shoptasticle.service.PriceFinderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LobsterGlovesPriceFinderTest {

    @Autowired
    private PriceFinderService priceFinderService;

    @MockBean
    AmazonPriceFinder amazonPriceFinder;

    Product lobsterGloves;

    @Before
    public void init() {
        lobsterGloves = new Product();
        lobsterGloves.setName("Pearl Izumi Lobster gloves SCREAMIN yellow");
        lobsterGloves.setTargetPrice(new BigDecimal("52.00"));
        lobsterGloves.addUrl("B06WLNT9MF");
        lobsterGloves.addUrl("https://www.backcountry.com/pearl-izumi-p.r.o.-amfib-lobster-gloves?CMP_SKU=PLZ009R&MER=0406&skid=PLZ009R-SCRYL-M&mr:trackingCode=FCADA246-E9F7-E811-8109-005056944E17&mr:referralID=NA&mr:device=c&mr:adType=plaonline&CMP_ID=PLA_GOc001&utm_source=Google&utm_medium=PLA&k_clickid=_k_CjwKCAiAsoviBRAoEiwATm8OYC-1TQVolUu9TP4sserK2Rx85KgcLUQYae6KoeqrlXO7h6tL5BX1tBoCfPMQAvD_BwE_k_");
    }

    @Test
    public void testIdealPriceFinder() {
        ProductWithPrices p = priceFinderService.findPrices(lobsterGloves);
        System.out.println("p.getPrices() = " + p.getPrices());
    }
}
