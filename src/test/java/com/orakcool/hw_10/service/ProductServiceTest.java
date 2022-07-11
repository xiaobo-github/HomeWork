package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.repository.impl.LaptopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProductServiceTest {

    private ProductService target;
    private LaptopRepository laptopRepositoryMock;
    private Laptop laptopMock;

    @BeforeEach
    void setUp() {
        laptopRepositoryMock = Mockito.mock(LaptopRepository.class);
        target = new ProductService(laptopRepositoryMock);
        laptopMock =  Mockito.mock(Laptop.class);
    }

    @Test
    void add() {
        target.add(laptopMock);
        Mockito.verify(laptopRepositoryMock).save(laptopMock);
    }

    @Test
    void add_addNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.add(null));
    }

    @Test
    void update() {
        target.update(laptopMock);
        Mockito.verify(laptopRepositoryMock,Mockito.atLeast(1)).update(Mockito.any());
    }

    @Test
    void update_addNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.update(null));
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(laptopRepositoryMock).getAll();
    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(laptopRepositoryMock).getAll();
    }

    @Test
    void delete() {
        target.delete("looks like an id");
        Mockito.verify(laptopRepositoryMock, Mockito.atLeastOnce()).delete(Mockito.any());
    }
}
