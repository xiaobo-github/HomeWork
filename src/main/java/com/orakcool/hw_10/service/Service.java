package com.orakcool.hw_10.service;

import com.orakcool.hw_10.Main;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.products.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public interface Service {
    int size();
    void add(Product product);
    void update(Product product);
    Optional<Product> findById(String id);
    List<Product> getAll();
    void printAll();

    boolean delete(String id);
}
