package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebPriceFinderTest {

    private WebPriceFinder backCountry = new WebPriceFinder("https://www.backcountry.com/pearl-izumi-p.r.o.-amfib-lobster-gloves?CMP_SKU=PLZ009R&MER=0406&skid=PLZ009R-SCRYL-M&mr:trackingCode=FCADA246-E9F7-E811-8109-005056944E17&mr:referralID=NA&mr:device=c&mr:adType=plaonline&CMP_ID=PLA_GOc001&utm_source=Google&utm_medium=PLA&k_clickid=_k_CjwKCAiAsoviBRAoEiwATm8OYC-1TQVolUu9TP4sserK2Rx85KgcLUQYae6KoeqrlXO7h6tL5BX1tBoCfPMQAvD_BwE_k_");

    @Test
    public void testLobsterGlovesGetPrice() {
        backCountry.findPrices(SearchCriteria.createStringSearchCriteria(""));
    }
}
