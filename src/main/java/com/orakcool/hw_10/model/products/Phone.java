package com.orakcool.hw_10.model.products;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.OperationSystem;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Phone extends Product {
    private String model;
    private Manufacturer manufacturer;
    private OperationSystem operatingSystem;
    private LocalDateTime created;
    private String priceCurrency;

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.PHONE);
        this.model = model;
        this.manufacturer = manufacturer;
        this.operatingSystem = new OperationSystem("doesn't have",0);
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Phone{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", " + operatingSystem.toString() +
                ", created=" + created +
                ", priceCurrency='" + priceCurrency + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
