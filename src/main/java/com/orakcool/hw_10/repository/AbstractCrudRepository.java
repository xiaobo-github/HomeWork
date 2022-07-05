package com.orakcool.hw_10.repository;

import com.orakcool.hw_10.model.Product;

import java.util.List;
import java.util.Optional;

public interface AbstractCrudRepository <P extends Product>{
    void save(P product);

    void saveAll(List<P> product);

    boolean update(P product);

    boolean delete(String id);

    List<P> getAll();

    Optional<P> findById(String id);
}
