package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.service.ProductFactory;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoStreamAPI implements Item {

    private final static String TYPE = "type";

    @Override
    public void run() {
        Stream<Product> productStream = getProductStream();
        AtomicInteger price = new AtomicInteger(500);

        System.out.printf("Products more expensive than %d:\n", price.get());
        productStream.filter(product -> product.getPrice() > price.get())
                .limit(5)
                .forEach(System.out::println);
        System.out.println("--------------------------------");

        productStream = getProductStream().limit(5);
        int sum = productStream.reduce(0, (x, y) -> x + 1, Integer::sum);
        System.out.printf("number of products: %d\n", sum);
        System.out.println("--------------------------------");

        System.out.println("Sort products by name, remove duplicates, convert to Map, where key is product id and value is product type:");
        Map<String, ProductType> map = getProductStream().limit(100)
                .sorted(Comparator.comparing(Product::getTitle))
                .limit(5)
                .distinct()
                .collect(Collectors.toMap(Product::getId, Product::getType));
        map.forEach((id, type) -> System.out.println("key - " + id + " value - " + type));
        System.out.println("--------------------------------");

        System.out.println("Checking for the presence of a specific detail from the list details in the stream:");
        getLaptopStream().filter(product -> product.getDetails()
                        .stream()
                        .anyMatch(detail -> detail.equals(Laptop.ALL_OF_THE_DETAILS[0])))
                .limit(5)
                .forEach(System.out::println);
        System.out.println("--------------------------------");

        System.out.println("Get statistics on the price of all products:");
        String statistic = getProductStream().limit(5)
                .mapToDouble(Product::getPrice)
                .summaryStatistics()
                .toString();
        System.out.println(statistic);
        System.out.println("--------------------------------");

        System.out.println("Check if a product has a price using the predicate:");
        Predicate<? super Product> isProductHavePrice = (product -> product.getPrice() > 0);
        getProductStream().filter(isProductHavePrice)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("--------------------------------");

        System.out.println("Convert Map<String, Object> to Product:");
        Function<Map<String, Object>, Product> mapToProduct = inputMap -> ProductFactory.createProduct((ProductType) inputMap.get(TYPE));
        Map<String, Object> productMap = Map.of(TYPE, ProductType.LAPTOP);
        System.out.println(mapToProduct.apply(productMap));
        System.out.println("--------------------------------");
    }

    private static Stream<Product> getProductStream() {
        return Stream.generate(() -> ProductFactory.createProduct(ProductFactory.getRandomType()));
    }

    private static Stream<Laptop> getLaptopStream() {
        return Stream.generate(() -> (Laptop) ProductFactory.createProduct(ProductType.LAPTOP));
    }
}
