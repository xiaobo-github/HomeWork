package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.Reader;
import com.orakcool.hw_10.util.SubProduct;

import static com.orakcool.hw_10.util.Reader.EXIT_FROM_MENU;
import static com.orakcool.hw_10.util.Reader.SET_EXIT_TO_ZERO;

public class Delete implements Item {
    @Override
    public void run() {
        String title = "What do you want to delete?";
        Reader.readMenu(title, ProductType.values(), Delete::chooseProduct);
    }

    private static void chooseProduct(int type){
        ProductType productType = ProductType.values()[type];
        String title = String.format("select the %s you want to remove:", productType.name().toLowerCase());

        SubProduct[] subProducts = ProductFactory.getItems(productType);
        int userInput = Reader.menu(title, subProducts, false);
        System.out.println(userInput);
        if(userInput != EXIT_FROM_MENU) {
            String id = subProducts[userInput - SET_EXIT_TO_ZERO].getId();
            ProductFactory.getProductService(productType).delete(id);
        }
    }

}
