package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

import java.util.Optional;
import java.util.Random;

public class RandomService {

    private Random random;

    RandomService(){
        random = new Random();
    }

    public int nextInt(int range){
        return random.nextInt(range);
    }

    public double nextDiscount(double range){
        return random.nextDouble(range);
    }

    public Optional<Product> getOptionalProduct(){
        final int losingChance = 3;
        Product[] products = {
                ProductFactory.createProduct(ProductType.LAPTOP),
                ProductFactory.createProduct(ProductType.PHONE),
                ProductFactory.createProduct(ProductType.ELECTRICSCOOTER)
        };

        if (random.nextInt(losingChance) != 1) return Optional.empty();

        return Optional.ofNullable(products[random.nextInt(products.length)]);
    }
}
