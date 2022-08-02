package com.orakcool.hw_10.app;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.util.ProductComparator;
import com.orakcool.hw_10.util.ProductsLinkedList;

import java.util.*;

public class ListApp {
    public static void run() {
        ProductsLinkedList<Product> myList = new ProductsLinkedList<>();
        List<Product> javaList = new LinkedList<>();

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

        for (int i=0; i<10; i++) {
            javaList.add(ProductFactory.createProduct(ProductType.values()[0]));
        }

        javaList.stream()
                .sorted(new ProductComparator<>())
                .forEach(product -> {
            System.out.println("price: " + product.getPrice()
                    + " title: " + product.getTitle()
                    + " count: " + product.getCount());
        });
    }
}
