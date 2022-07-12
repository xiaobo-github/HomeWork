package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductFactoryTest {

    @BeforeEach
    void setUp(){
    }

    @Test
    void createProduct() {
        final ProductType[] testTypes = ProductType.values();
        for (ProductType type: testTypes) {
            Assertions.assertEquals(type,ProductFactory.createProduct(type).getType());
        }
    }

    @Test
    void getRandomManufacturer() {
        Assertions.assertEquals(Manufacturer.class,ProductFactory.getRandomManufacturer().getClass());
    }
}