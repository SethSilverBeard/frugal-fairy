package com.shoptasticle.pricefinder.integration;

import com.shoptasticle.amazon.mws.client.Credentials;
import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.ProductWithPrices;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
import com.shoptasticle.pricefinder.WebPriceFinder;
import com.shoptasticle.service.PriceFinderService;
import com.shoptasticle.service.PriceFinderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest(classes = {
        PriceFinderServiceImpl.class,
        WebPriceFinder.class,
        AmazonPriceFinder.class
})
@EnableConfigurationProperties(Credentials.class)
@RunWith(SpringRunner.class)
public class LobsterGlovesPriceFinderTest {

    @Autowired
    private PriceFinderService priceFinderService;

    Product lobsterGloves;

    @Before
    public void init() {
        lobsterGloves = new Product();
        lobsterGloves.setName("Pearl Izumi Lobster gloves SCREAMIN yellow");
        lobsterGloves.setTargetPrice(new BigDecimal("52.00"));
        lobsterGloves.addUrl("B06WLNT9MF");
        lobsterGloves.addUrl("https://www.backcountry.com/pearl-izumi-p.r.o.-amfib-lobster-gloves?CMP_SKU=PLZ009R&MER=0406&skid=PLZ009R-SCRYL-M&mr:trackingCode=FCADA246-E9F7-E811-8109-005056944E17&mr:referralID=NA&mr:device=c&mr:adType=plaonline&CMP_ID=PLA_GOc001&utm_source=Google&utm_medium=PLA&k_clickid=_k_CjwKCAiAsoviBRAoEiwATm8OYC-1TQVolUu9TP4sserK2Rx85KgcLUQYae6KoeqrlXO7h6tL5BX1tBoCfPMQAvD_BwE_k_");
        lobsterGloves.addUrl("https://www.coloradocyclist.com/pearl-izumi-p-r-o-amfib-lobster-glove-md-screaming-yellow");
        lobsterGloves.addUrl("https://www.competitivecyclist.com/pearl-izumi-p.r.o.-amfib-lobster-gloves?skid=PLZ009R-SCRYL-M&CMP_SKU=PLZ009R&MER=0406&CSPID=0914&mr:trackingCode=1001AB50-E9F7-E811-8100-0050569428E8&mr:referralID=NA&mr:device=c&mr:adType=plaonline&mr:ad=185076965647&mr:keyword=&mr:match=&mr:tid=pla-453938964932&mr:ploc=9028820&mr:iloc=&mr:store=&mr:filter=453938964932&CMP_ID=PLA_GOc014&CSPID=0914&utm_source=Google&utm_medium=PLA&k_clickid=_k_EAIaIQobChMIgKb8zbWA4AIVSbbACh108wdFEAYYCSABEgJsr_D_BwE_k_&gclid=EAIaIQobChMIgKb8zbWA4AIVSbbACh108wdFEAYYCSABEgJsr_D_BwE");
    }

    @Test
    public void testIdealPriceFinder() {
        ProductWithPrices p = priceFinderService.findPrices(lobsterGloves);
        p.getGoodPrices(); //outputs DEBUG lines
    }
}
