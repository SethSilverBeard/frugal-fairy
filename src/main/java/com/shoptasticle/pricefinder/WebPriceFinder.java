package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.Price;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WebPriceFinder implements PriceFinder {

    private static final Logger logger = LogManager.getLogger(WebPriceFinder.class);

    //TODO: Move this to a singleton bean and inject.
    @PostConstruct
    public void init() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public List<Price> findPrices(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        List<WebElement> priceElements = driver.findElements(By.xpath("//*[not(self::script or self::style) and starts-with(text(), '$')]"));
        Optional<WebElement> firstVisiblePrice = priceElements.stream()
                .filter(e -> e.isDisplayed())
                .findFirst();
        if (firstVisiblePrice.isPresent()) {
            String priceString = firstVisiblePrice.get().getText().replace("$", "");
            try {
                Price price = new Price(new BigDecimal(priceString));
                price.setUrl(url);
                price.setSellerName(parseHostName(url));
                return Arrays.asList(price);
            } catch(NumberFormatException e) {
                logger.error("Unable to parse price [{}] from [{}]", priceString, url, e);
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    private String parseHostName(String url) {
        try {
            URI uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
