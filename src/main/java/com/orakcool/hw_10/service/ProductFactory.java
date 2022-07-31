package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.model.products.DiscountCard;
import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.model.products.Phone;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.Random;

public class ProductFactory {

    private ProductFactory() {

    }

    @SneakyThrows
    public static Product createProduct(ProductType type){
        Random RANDOM = new Random();

        return switch (type){
            case PHONE -> new Phone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            );
            case LAPTOP -> new Laptop(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            );
            case ELECTRICSCOOTER -> new ElectricScooter(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            );
            case DISCOUNTCARD -> new DiscountCard();
            default -> throw new IllegalArgumentException("Unknown product type: " + type);
        };

    }

    public static Manufacturer getRandomManufacturer() {
        Random RANDOM = new Random();

        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

}
