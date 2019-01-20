package com.shoptasticle.domain;

import com.shoptasticle.domain.Price;

import java.math.BigDecimal;
import java.util.*;

/**
 * MwsProduct has several prices from different stores
 */
public class Product {
    String name;
    BigDecimal targetPrice;
    Set<Price> prices = new TreeSet<>();

    public List<Price> getGoodPrices() {
        if (prices == null || prices.isEmpty()) {
            return Collections.emptyList();
        }
        List<Price> goodPrices = new ArrayList<>();
        for (Price price: prices) {
            if (price.getTotal() == null || price.getTotal().compareTo(targetPrice) > 0 ) {
                continue;
            } else {
               goodPrices.add(price);
            }
        }
        return goodPrices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
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
}
