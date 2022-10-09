package com.orakcool.hw_18.repository;

import com.orakcool.hw_18.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private static ProductRepository repository;
    private final Map<Long, Product> storage = new HashMap<>();

    public static ProductRepository getInstance() {
        if (repository == null) {
            repository = new ProductRepository();
        }
        return repository;
    }

    public Product save(Product product) {
        return storage.put(product.getId(), product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(storage.values());
    }

    private ProductRepository() {
    }
}
