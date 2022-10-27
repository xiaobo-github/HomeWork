package com.orakcool.hw_10.service;

import com.orakcool.hw_10.annotations.Autowired;
import com.orakcool.hw_10.annotations.Singleton;
import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.repository.impl.ElectricScooterRepository;

@Singleton
public class ElectricScooterService extends ProductService<ElectricScooter> {
    @Autowired
    public ElectricScooterService(ElectricScooterRepository repository) {
        super(repository);
    }
}
