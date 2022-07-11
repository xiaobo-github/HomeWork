package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.repository.AbstractCrudRepository;

import java.util.List;

public class ProductService {

    private AbstractCrudRepository REPOSITORY;

    public ProductService(AbstractCrudRepository repository) {
        REPOSITORY = repository;
    }

    public void add(Product product) {
        if(product == null){
            throw new IllegalArgumentException("added product must not be null");
        }
        REPOSITORY.save(product);
    }

    public void update(Product product) {
        if(product == null){
            throw new IllegalArgumentException("updated product must not be null");
        }
        REPOSITORY.update(product);
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
