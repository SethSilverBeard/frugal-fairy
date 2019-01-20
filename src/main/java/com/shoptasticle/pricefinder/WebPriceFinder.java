package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.Price;
import com.shoptasticle.domain.SearchCriteria;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class WebPriceFinder implements PriceFinder {

    static {
        WebDriverManager.chromedriver().setup();
    }

    private final String url;

    private WebDriver driver = new ChromeDriver();

    public WebPriceFinder(String url) {
        this.url = url;
    }

    @Override
    public List<Price> findPrices(SearchCriteria searchCriteria) {
        driver.get(url);
        String priceString = driver.findElement(By.cssSelector(".product-pricing__sale.js-product-pricing__sale")).getText();
        Price price = new Price(new BigDecimal(priceString.replace("$","")));
        System.out.println("price = " + price);
        return Arrays.asList(price);
    }
}
