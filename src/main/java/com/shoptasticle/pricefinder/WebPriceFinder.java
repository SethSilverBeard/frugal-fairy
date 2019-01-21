package com.shoptasticle.pricefinder;

import com.shoptasticle.domain.Price;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class WebPriceFinder implements PriceFinder {

    @Override
    public List<Price> findPrices(String url) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        //String priceString = //driver.findElement(By.cssSelector(".product-pricing__sale.js-product-pricing__sale")).getText();
        List<WebElement> priceElements = driver.findElements(By.xpath("//*[not(self::script or self::style) and starts-with(text(), '$')]"));
        //System.out.println("priceElements = " + priceElements);
        Price price = new Price(new BigDecimal(priceElements.get(0).getText().replace("$","")));
        return Arrays.asList(price);
    }
}
