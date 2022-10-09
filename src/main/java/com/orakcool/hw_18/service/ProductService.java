package com.orakcool.hw_18.service;

import com.orakcool.hw_18.model.Product;
import com.orakcool.hw_18.model.ProductNotifiable;
import com.orakcool.hw_18.repository.ProductRepository;
import com.orakcool.hw_18.util.ProductFactory;

import java.util.List;
import java.util.stream.Stream;

public class ProductService {
    private static final ProductRepository repository = ProductRepository.getInstance();

    public static void createProduct(int count) {
        if (count < 1) {
            throw new RuntimeException("count must be greater than zero");
        }

        Stream.generate(ProductFactory::generateRandomProduct)
                .limit(count)
                .forEach(ProductService::saveProduct);
    }

    public static void saveProduct(Product product) {
        repository.save(product);
    }

    public static List<Product> getAll() {
        return repository.getAll();
    }

    public static int sendNotifications() {
        return Math.toIntExact(repository.getAll()
                .stream()
                .filter(it -> it instanceof ProductNotifiable)
                .map(it -> (ProductNotifiable) it)
                .count());
    }

    private ProductService() {
    }
}
