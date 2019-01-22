package com.shoptasticle.service;

import com.shoptasticle.domain.Price;
import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.ProductWithPrices;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
import com.shoptasticle.pricefinder.PriceFinder;
import com.shoptasticle.pricefinder.WebPriceFinder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceFinderServiceImpl implements PriceFinderService {

    private final AmazonPriceFinder amazonPriceFinder;
    private final WebPriceFinder webPriceFinder;

    public PriceFinderServiceImpl(AmazonPriceFinder amazonPriceFinder, WebPriceFinder webPriceFinder) {
        this.amazonPriceFinder = amazonPriceFinder;
        this.webPriceFinder = webPriceFinder;
    }

    @Override
    public ProductWithPrices findPrices(Product product) {
        ProductWithPrices p = new ProductWithPrices(product);
        for (String url: product.getUrls()) {
            PriceFinder priceFinder = createPriceFinder(url);
            List<Price> prices = priceFinder.findPrices(url);
            p.addAllPrices(prices);
        }
        return p;
    }

    protected PriceFinder createPriceFinder(String url) {
        if (url.startsWith("http")) {
            return webPriceFinder;
        } else {
            return amazonPriceFinder;
        }
    }
}
