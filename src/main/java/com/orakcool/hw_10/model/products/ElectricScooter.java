package com.orakcool.hw_10.model.products;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectricScooter extends Product {
    private final String model;
    private final Manufacturer manufacturer;

    public ElectricScooter(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.ELECTRICSCOOTER);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Electric_scooter{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
