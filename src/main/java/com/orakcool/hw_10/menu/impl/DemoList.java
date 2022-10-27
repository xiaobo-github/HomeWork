package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.collections.ProductComparator;
import com.orakcool.hw_10.util.collections.ProductLinkedList;

import java.util.ArrayList;
import java.util.List;

public class DemoList implements Item {
    @Override
    public void run() {
        ProductLinkedList<Product> myList = new ProductLinkedList<>();
        List<Product> javaList = new ArrayList<>();

        myList.add(ProductFactory.createProduct(ProductType.LAPTOP), 1);
        myList.add(ProductFactory.createProduct(ProductType.PHONE), 2);
        myList.add(ProductFactory.createProduct(ProductType.ELECTRICSCOOTER), 3);
        myList.forEach(System.out::println);
        System.out.println("index find by version: " + myList.indexOf(2));
        System.out.println("---");

        myList.remove(2);
        myList.forEach(System.out::println);
        System.out.println("index find by version: " + myList.indexOf(2));
        System.out.println("---");

        for (int i = 0; i < 10; i++) {
            javaList.add(ProductFactory.createProduct(ProductType.values()[0]));
        }

        javaList.stream()
                .sorted(new ProductComparator<>())
                .forEach(product -> {
                    System.out.println("price: " + product.getPrice()
                            + " title: " + product.getTitle()
                            + " count: " + product.getCount());
                });
        System.out.println();
    }
}
