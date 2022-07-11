package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.model.products.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    private static Product product;
    private static ProductFactory productFactory;

    @BeforeEach
    void setUp(){
        productFactory = Mockito.mock(ProductFactory.class);
    }

    @Test
    void createProduct() {
        Mockito.when(productFactory).thenCallRealMethod();
    }

    @Test
    void getRandomManufacturer() {
    }
}