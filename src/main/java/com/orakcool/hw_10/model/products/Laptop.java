package com.orakcool.hw_10.model.products;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.Product;

import com.orakcool.hw_10.model.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Laptop extends Product {
    private final String model;
    private final Manufacturer manufacturer;

    public Laptop(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.LAPTOP);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
