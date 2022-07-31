package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.repository.AbstractCRUDRepository;

import java.util.List;

public class ProductService<T extends Product> {

    private AbstractCRUDRepository<T> repository;

    public ProductService(AbstractCRUDRepository<T> repository) {
        this.repository = repository;
    }

    public void add(T product) {
        if(product == null){
            throw new IllegalArgumentException("added product must not be null");
        }
        repository.save(product);
    }

    public void update(T product) {
        if(product == null){
            throw new IllegalArgumentException("updated product must not be null");
        }
        repository.update(product);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public void printAll() {
        repository.getAll().forEach(System.out::println);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}
