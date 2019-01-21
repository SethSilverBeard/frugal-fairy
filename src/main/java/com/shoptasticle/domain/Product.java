package com.shoptasticle.domain;

import java.math.BigDecimal;
import java.util.*;

/**
 * MwsProduct has several prices from different stores
 */
public class Product {
    String name;
    BigDecimal targetPrice;
    List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
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

    public void addUrl(String url) {
        if (urls == null || urls.isEmpty()) {
            urls = new ArrayList<>();
        }
        urls.add(url);
    }
}
