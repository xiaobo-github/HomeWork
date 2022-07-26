package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.repository.AbstractCRUDRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ProductServiceTest {

    private ProductService target;
    private AbstractCRUDRepository repositoryMock;
    private Product productMock;

    @BeforeEach
    void setUp() {
        repositoryMock = Mockito.mock(new AbstractCRUDRepository(){

            private static List<Product> products = new ArrayList<>();

            @Override
            public void save(Product product) {
                products.add(product);
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
                return products;
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
        Mockito.doCallRealMethod().when(repositoryMock).save(productMock);
        Mockito.when(repositoryMock.getAll()).thenCallRealMethod();

        target.add(productMock);

        Mockito.verify(repositoryMock).save(productMock);
        Assertions.assertEquals(1,repositoryMock.getAll().size());
    }

    @Test
    void add_addNull() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.add(null));
    }

    @Test
    void update(){
        final String title = "product for test";
        Product product = new Product(title,1,5.5,ProductType.values()[0]) {
            @Override
            public String getId() {
                return super.getId();
            }
        };
        target.update(product);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        Mockito.verify(repositoryMock).update(productArgumentCaptor.capture());

        Assertions.assertEquals(title,productArgumentCaptor.getValue().getTitle());
        Assertions.assertEquals(1,productArgumentCaptor.getValue().getCount());
    }

    @Test
    void update_addNull() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.update(null));
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
        final String id = "looks like an id";
        Mockito.when(repositoryMock.delete(Mockito.eq(id))).thenReturn(true);

        Assertions.assertEquals(true,target.delete(id));
    }
}
