package com.orakcool.hw_10.service;

import com.orakcool.hw_10.annotations.Autowired;
import com.orakcool.hw_10.annotations.Singleton;
import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.repository.impl.LaptopRepository;

@Singleton
public class LaptopService extends ProductService<Laptop> {
    @Autowired
    public LaptopService(LaptopRepository repository) {
        super(repository);
    }
}
