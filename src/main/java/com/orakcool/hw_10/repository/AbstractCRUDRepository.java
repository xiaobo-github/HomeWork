package com.orakcool.hw_10.repository;

import com.orakcool.hw_10.model.Product;

import java.util.List;
import java.util.Optional;

public interface AbstractCRUDRepository<T extends Product> {
    void save(T product);

    void saveAll(List<T> product);

    boolean update(T product);

    boolean delete(String id);

    List<T> getAll();

    Optional<T> findById(String id);
}
