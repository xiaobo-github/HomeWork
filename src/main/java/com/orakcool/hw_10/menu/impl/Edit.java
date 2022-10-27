package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.Menu;
import com.orakcool.hw_10.util.Reader;
import com.orakcool.hw_10.util.SubProduct;

import static com.orakcool.hw_10.util.Reader.EXIT_FROM_MENU;
import static com.orakcool.hw_10.util.Reader.SET_EXIT_TO_ZERO;

public class Edit implements Item {
    @Override
    public void run() {
        String title = "What do you want to edit:";
        Reader.readMenu(title, ProductType.values(), Edit::editProduct);
    }

    private static void editProduct(int type) {
        ProductType productType = ProductType.values()[type];
        String title = String.format("Select the %s you want to edit:", productType.name().toLowerCase());

        SubProduct[] subProducts = ProductFactory.getItems(productType);
        int userInput = Reader.menu(title, subProducts, false);
        System.out.println(userInput);
        if (userInput != EXIT_FROM_MENU) {
            String id = subProducts[userInput - SET_EXIT_TO_ZERO].getId();
            title = "What do you want to change:";
            int item = Reader.menu(title, Menu.EditMenuItems.values(), false);
            if (item != 0) {
                switch (Menu.EditMenuItems.values()[item - SET_EXIT_TO_ZERO]) {
                    case COUNT -> count(productType, id);
                    case PRICE -> price(productType, id);
                    case TITLE -> title(productType, id);
                }
            }
        }
    }

    private static void count(ProductType productType, String id) {
        int productCount = Reader.readInt("Enter new product count:");
        ProductFactory.getProductService(productType)
                .get(id)
                .setCount(productCount);
    }

    private static void title(ProductType productType, String id) {
        String productTitle = Reader.readLine("Enter new title:");
        ProductFactory.getProductService(productType)
                .get(id)
                .setTitle(productTitle);
    }

    private static void price(ProductType productType, String id) {
        Double productPrice = Reader.readDouble("Enter new price:");
        ProductFactory.getProductService(productType)
                .get(id)
                .setPrice(productPrice);
    }
}
