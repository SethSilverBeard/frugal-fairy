package com.shoptasticle.domain;

import java.util.*;

public class ProductWithPrices extends Product {

    Set<Price> prices = new TreeSet<>();

    public ProductWithPrices(Product p) {
        this.name = p.name;
        this.targetPrice = p.targetPrice;
        this.urls = p.urls;
    }

    public ProductWithPrices() {
    }

    public List<Price> getGoodPrices() {
        if (prices == null || prices.isEmpty()) {
            return Collections.emptyList();
        }
        List<Price> goodPrices = new ArrayList<>();
        for (Price price : prices) {
            if (price.getTotal() == null || price.getTotal().compareTo(targetPrice) > 0) {
                continue;
            } else {
                goodPrices.add(price);
            }
        }
        return goodPrices;
    }

    public List<Price> getPrices() {
        return new ArrayList<>(prices);
    }

    public void setPrices(List<Price> prices) {
        this.prices.clear();
        this.prices.addAll(prices);
    }

    public void addPrice(Price price) {
        prices.add(price);
    }

    public void addAllPrices(List<Price> prices) {
        this.prices.addAll(prices);
    }
}
