package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.repository.AbstractCrudRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private AbstractCrudRepository REPOSITORY;

    public ProductService(AbstractCrudRepository repository) {
        REPOSITORY = repository;
    }

    public int size() {
        return REPOSITORY.getAll().size();
    }

    public void add(Product product) {
        REPOSITORY.save(product);
    }

    public void update(Product product) {
        REPOSITORY.update(product);
    }

    public Optional<Product> findById(String id) {
        return Optional.of((Product)REPOSITORY.findById(id).get());
    }

    public List<Product> getAll() {
        return REPOSITORY.getAll();
    }

    public void printAll() {
        REPOSITORY.getAll().forEach(product -> System.out.println(product));
    }

    public boolean delete(String id) {
        return REPOSITORY.delete(id);
    }
}
