package com.orakcool.hw_10.model.products;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectricScooter extends Product {
    private String model;
    private Manufacturer manufacturer;

    private ElectricScooter(String title, int count, double price, ProductType productType) {
        super(title, count, price, productType);
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

    public static class Builder {
        private final ElectricScooter newElectricScooter;

        public Builder(double price, ProductType productType) {
            String title = "";
            int count = 0;
            newElectricScooter = new ElectricScooter(title, count, price, productType);
        }

        public Builder title(String title) {
            if (title.length() > 20) {
                throw new IllegalArgumentException("title must be no more than 20 characters");
            }
            newElectricScooter.setTitle(title);
            return this;
        }

        public Builder count(int count) {
            if (count < 1) {
                throw new IllegalArgumentException("count must be greater than zero");
            }
            newElectricScooter.setCount(count);
            return this;
        }

        public Builder model(String model) {
            newElectricScooter.setModel(model);
            return this;
        }

        public Builder manufacturer(Manufacturer manufacturer) {
            newElectricScooter.setManufacturer(manufacturer);
            return this;
        }

        public ElectricScooter build() {
            return newElectricScooter;
        }

    }
}
