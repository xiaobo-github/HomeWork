package com.orakcool.hw_10.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class

Product {
    protected final String id;
    protected String title;
    protected int count;
    protected double price;

    protected final ProductType type;

    protected Product(String title, int count, double price, ProductType type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.count = count;
        this.price = price;
        this.type = type;
    }
}
