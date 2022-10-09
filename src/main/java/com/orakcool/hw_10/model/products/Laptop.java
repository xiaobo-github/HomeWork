package com.orakcool.hw_10.model.products;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Laptop extends Product {
    public static final String[] ALL_OF_THE_DETAILS = {"camera", "memory card", "ssd"};
    private final String model;
    private final Manufacturer manufacturer;

    List<String> details;

    public Laptop(String title, int count, double price, String model, Manufacturer manufacturer, List<String> details) {
        super(title, count, price, ProductType.LAPTOP);
        this.model = model;
        this.manufacturer = manufacturer;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", details{"+
                String.join(", ", details) +
                '}'+
                '}';
    }
}
