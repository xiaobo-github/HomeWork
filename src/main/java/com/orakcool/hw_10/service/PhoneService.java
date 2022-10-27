package com.orakcool.hw_10.service;

import com.orakcool.hw_10.annotations.Autowired;
import com.orakcool.hw_10.annotations.Singleton;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.repository.impl.PhoneRepository;

@Singleton
public class PhoneService extends ProductService<Phone> {
    @Autowired
    public PhoneService(PhoneRepository repository) {
        super(repository);
    }
}
