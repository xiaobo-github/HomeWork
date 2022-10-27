package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.Reader;

public class Create implements Item {
    @Override
    public void run() {
        String title = "What do you want to create?";
        Reader.readMenu(title, ProductType.values(), Create::createProduct);
    }

    private static void createProduct(int input) {
        ProductFactory.createAndSaveProduct(ProductType.values()[input]);
    }
}
