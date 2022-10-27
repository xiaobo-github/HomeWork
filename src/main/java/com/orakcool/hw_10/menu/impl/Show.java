package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.Reader;

public class Show implements Item {
    @Override
    public void run() {
        String title = "What do you want to show?";
        Reader.readMenu(title, ProductType.values(), Show::showProducts);
    }

    private static void showProducts(int input) {
        ProductFactory.show(ProductType.values()[input]);
    }
}
