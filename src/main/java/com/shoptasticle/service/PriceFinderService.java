package com.shoptasticle.service;

import com.shoptasticle.domain.Product;
import com.shoptasticle.domain.ProductWithPrices;

public interface PriceFinderService {

    ProductWithPrices findPrices(Product product);

}
