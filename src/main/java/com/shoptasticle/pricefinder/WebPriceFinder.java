package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.Price;
import io.github.bonigarcia.wdm.WebDriverManager;
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

    WebDriver driver;

    //TODO: Move this to a singleton bean and inject.
    @PostConstruct
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Override
    public List<Price> findPrices(String url) {
        driver.get(url);
        List<WebElement> priceElements = driver.findElements(By.xpath("//*[not(self::script or self::style) and starts-with(text(), '$')]"));
        Optional<WebElement> firstVisiblePrice = priceElements.stream()
                .filter(e -> e.isDisplayed())
                .findFirst();
        if (firstVisiblePrice.isPresent()) {
            Price price = new Price(new BigDecimal(firstVisiblePrice.get().getText().replace("$", "")));
            price.setUrl(url);
            price.setSellerName(parseHostName(url));
            return Arrays.asList(price);
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
