package com.orakcool.hw_18.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Product {
    private final long id;
    private final boolean available;
    private final String title;
    private final double price;
    private final String channel;

    protected String getBasicInfo() {
        return "Product{" +
                "id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", channel='" + channel + '\'';
    }

    @Override
    public String toString() {
        return getBasicInfo() +
                '}';
    }
}
