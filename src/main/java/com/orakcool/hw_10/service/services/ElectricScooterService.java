package com.orakcool.hw_10.service.services;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.repository.impl.ElecricScooterRepository;
import com.orakcool.hw_10.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElectricScooterService implements Service {

    private static final ElecricScooterRepository REPOSITORY = new ElecricScooterRepository();

    @Override
    public int size() {
        return REPOSITORY.getAll().size();
    }

    @Override
    public void add(Product product) {
        REPOSITORY.save((ElectricScooter) product);
    }

    @Override
    public void update(Product product) {
        REPOSITORY.update((ElectricScooter) product);
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
        for (ElectricScooter electricScooter : REPOSITORY.getAll()) {
            System.out.println(electricScooter);
        }
    }

    @Override
    public boolean delete(String id) {
        return REPOSITORY.delete(id);
    }
}
