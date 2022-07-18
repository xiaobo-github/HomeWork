package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ProductFactoryTest {

    @BeforeEach
    void setUp(){
    }

    @ParameterizedTest
    @EnumSource(ProductType.class)
    void createProduct(ProductType type) {
        Assertions.assertEquals(type, ProductFactory.createProduct(type).getType());
    }

    @Test
    void getRandomManufacturer() {
        Assertions.assertEquals(Manufacturer.class,ProductFactory.getRandomManufacturer().getClass());
    }
}