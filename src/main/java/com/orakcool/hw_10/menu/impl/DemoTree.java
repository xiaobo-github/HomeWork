package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.collections.SimpleProductTree;

public class DemoTree implements Item {
    @Override
    public void run() {
        SimpleProductTree<Phone> myTree = new SimpleProductTree<>();
        for (int i = 0; i < 20; i++) {
            myTree.add((Phone) ProductFactory.createProduct(ProductType.PHONE));
        }

        System.out.println("Product tree:");
        System.out.println(myTree);
        System.out.println("----------");

        System.out.println("Show branch costs:");
        System.out.println(myTree.costToString());
    }
}
