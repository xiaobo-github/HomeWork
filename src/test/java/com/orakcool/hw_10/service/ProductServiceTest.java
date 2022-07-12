package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.repository.AbstractCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class ProductServiceTest {

    private ProductService target;
    private AbstractCrudRepository repositoryMock;
    private Product productMock;

    @BeforeEach
    void setUp() {
        repositoryMock = Mockito.mock(new AbstractCrudRepository(){

            @Override
            public void save(Product product) {

            }

            @Override
            public void saveAll(List product) {

            }

            @Override
            public boolean update(Product product) {
                return false;
            }

            @Override
            public boolean delete(String id) {
                return false;
            }

            @Override
            public List getAll() {
                return null;
            }

            @Override
            public Optional findById(String id) {
                return Optional.empty();
            }
        }.getClass());
        target = new ProductService(repositoryMock);
        productMock =  Mockito.mock(Product.class);
    }

    @Test
    void add() {
        target.add(productMock);
        Mockito.verify(repositoryMock).save(productMock);
    }

    @Test
    void add_addNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.add(null));
    }

    @Test
    void update() {
        Mockito.when(repositoryMock.update(productMock)).thenCallRealMethod();

        target.update(productMock);

        Mockito.verify(repositoryMock).update(productMock);
    }

    @Test
    void update_addNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.update(null));
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repositoryMock).getAll();
    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repositoryMock).getAll();
    }

    @Test
    void delete() {
        target.delete("looks like an id");
        Mockito.verify(repositoryMock, Mockito.atLeastOnce()).delete(Mockito.any());
    }
}
