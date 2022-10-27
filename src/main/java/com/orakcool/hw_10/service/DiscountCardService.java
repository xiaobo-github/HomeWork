package com.orakcool.hw_10.service;

import com.orakcool.hw_10.annotations.Autowired;
import com.orakcool.hw_10.annotations.Singleton;
import com.orakcool.hw_10.model.products.DiscountCard;
import com.orakcool.hw_10.repository.impl.DiscountCardRepository;

@Singleton
public class DiscountCardService extends ProductService<DiscountCard> {
    @Autowired
    public DiscountCardService(DiscountCardRepository repository) {
        super(repository);
    }
}
