package com.orakcool.hw_10.service.services;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.repository.impl.LaptopRepository;
import com.orakcool.hw_10.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LaptopService implements Service {

    private static final LaptopRepository REPOSITORY = new LaptopRepository();
    @Override
    public int size() {
        return REPOSITORY.getAll().size();
    }
    @Override
    public void add(Product product) {
        REPOSITORY.save((Laptop) product);
    }

    @Override
    public void update(Product product) {
        REPOSITORY.update((Laptop) product);
    }

    public Optional<Product> findById(String id) {
        if(REPOSITORY.findById(id).isEmpty()) return Optional.empty();
        return Optional.of(REPOSITORY.findById(id).get());
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<Product>(REPOSITORY.getAll());
    }

    @Override
    public void printAll() {
        for (Laptop laptop : REPOSITORY.getAll()) {
            System.out.println(laptop);
        }
    }

    @Override
    public boolean delete(String id) {
        return REPOSITORY.delete(id);
    }
}
