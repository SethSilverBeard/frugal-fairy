package com.shoptasticle.service;

import com.shoptasticle.domain.Price;
import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.ProductWithPrices;
import com.shoptasticle.pricefinder.AmazonPriceFinder;
import com.shoptasticle.pricefinder.PriceFinder;
import com.shoptasticle.pricefinder.WebPriceFinder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class PriceFinderServiceImpl implements PriceFinderService {

    ExecutorService executorService = Executors.newCachedThreadPool();

    private final AmazonPriceFinder amazonPriceFinder;
    private final WebPriceFinder webPriceFinder;

    public PriceFinderServiceImpl(AmazonPriceFinder amazonPriceFinder, WebPriceFinder webPriceFinder) {
        this.amazonPriceFinder = amazonPriceFinder;
        this.webPriceFinder = webPriceFinder;
    }

    @Override
    public ProductWithPrices findPrices(Product product) {
        ProductWithPrices p = new ProductWithPrices(product);
        List<Future<List<Price>>> futures = new ArrayList<>();
        for (String url : product.getUrls()) {
            futures.add(executorService.submit(() -> {
                        PriceFinder priceFinder = createPriceFinder(url);
                        return priceFinder.findPrices(url);
                    }
            ));
        }
        for (Future<List<Price>> f : futures) {
            try {
                p.addAllPrices(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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
