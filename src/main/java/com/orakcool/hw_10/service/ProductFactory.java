package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.model.products.DiscountCard;
import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.repository.impl.DiscountCardRepository;
import com.orakcool.hw_10.repository.impl.ElecricScooterRepository;
import com.orakcool.hw_10.repository.impl.LaptopRepository;
import com.orakcool.hw_10.repository.impl.PhoneRepository;
import com.orakcool.hw_10.util.SubProduct;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductFactory {
    private static final ProductService<Phone> PHONE_SERVICE = new ProductService<>(new PhoneRepository());
    private static final ProductService<Laptop> LAPTOP_SERVICE = new ProductService<>(new LaptopRepository());
    private static final ProductService<ElectricScooter> ELECTRIC_SCOOTER_SERVICE = new ProductService<>(new ElecricScooterRepository());
    private static final ProductService<DiscountCard> DISCOUNT_CARD_SERVICE = new ProductService<>(new DiscountCardRepository());

    private ProductFactory() {
    }

    @SneakyThrows
    public static Product createProduct(ProductType type) {
        Random random = new Random();

        return switch (type) {
            case PHONE -> new Phone(
                    "Title-" + random.nextInt(1000),
                    random.nextInt(500),
                    random.nextDouble(1000.0),
                    "Model-" + random.nextInt(10),
                    getRandomManufacturer()
            );
            case LAPTOP -> new Laptop(
                    "Title-" + random.nextInt(1000),
                    random.nextInt(500),
                    random.nextDouble(1000.0),
                    "Model-" + random.nextInt(10),
                    getRandomManufacturer(),
                    random.nextInt(100) > 50 ? new ArrayList<>() : new ArrayList<>(List.of(Laptop.ALL_OF_THE_DETAILS[random.nextInt(Laptop.ALL_OF_THE_DETAILS.length)]))
            );
            case ELECTRICSCOOTER -> new ElectricScooter(
                    "Title-" + random.nextInt(1000),
                    random.nextInt(500),
                    random.nextDouble(1000.0),
                    "Model-" + random.nextInt(10),
                    getRandomManufacturer()
            );
            case DISCOUNTCARD -> new DiscountCard();
            default -> throw new IllegalArgumentException("Unknown product type: " + type);
        };
    }

    public static void createAndSaveProduct(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.add((Phone) createProduct(type));
            case LAPTOP -> LAPTOP_SERVICE.add((Laptop) createProduct(type));
            case ELECTRICSCOOTER -> ELECTRIC_SCOOTER_SERVICE.add((ElectricScooter) createProduct(type));
            case DISCOUNTCARD -> DISCOUNT_CARD_SERVICE.add((DiscountCard) createProduct(type));
            default -> throw new IllegalArgumentException("Unknown product type: " + type);
        }
        ;
    }

    public static void show(ProductType type) {
        ProductService<? extends Product> service = getProductService(type);
        String products = service.getAll().stream().map((Product p) -> p.toString()).collect(Collectors.joining("\n"));

        System.out.println(products);
    }

    public static SubProduct[] getItems(ProductType type) {
        List<? extends Product> list = getProductService(type).getAll();

        return list.stream()
                .map(product -> new SubProduct(product.toString(), product.getId()))
                .toArray(SubProduct[]::new);
    }

    public static Manufacturer getRandomManufacturer() {
        Random random = new Random();

        final Manufacturer[] values = Manufacturer.values();
        final int index = random.nextInt(values.length);
        return values[index];
    }

    public static ProductType getRandomType() {
        Random random = new Random();

        final ProductType[] values = ProductType.values();
        final int index = random.nextInt(values.length);
        return values[index];
    }

    public static ProductService<? extends Product> getProductService(ProductType type) {
        return switch (type) {
            case PHONE -> PHONE_SERVICE;
            case LAPTOP -> LAPTOP_SERVICE;
            case ELECTRICSCOOTER -> ELECTRIC_SCOOTER_SERVICE;
            case DISCOUNTCARD -> DISCOUNT_CARD_SERVICE;
            default -> throw new IllegalArgumentException("Unknown product type: " + type);
        };
    }
}
