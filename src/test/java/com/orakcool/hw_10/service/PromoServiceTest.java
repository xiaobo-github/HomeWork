package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

class PromoServiceTest {
    PromoService promoService;
    RandomService randoms;

    @BeforeEach
    void setUp() {
        randoms = Mockito.mock(RandomService.class);
        promoService = new PromoService(randoms);
    }

    @Test
    void thousandthCustomer_ifThousandth() {
        Mockito.when(randoms.getOptionalProduct())
                .thenReturn(Optional.ofNullable(ProductFactory.createProduct(ProductType.values()[0])));

        promoService.thousandthCustomer();
        Mockito.verify(randoms,Mockito.atLeastOnce()).getOptionalProduct();
        Assertions.assertTrue(randoms.getOptionalProduct().isPresent());
    }

    @Test
    void thousandthCustomer_ifNot() {
        Mockito.when(randoms.getOptionalProduct())
                .thenReturn(Optional.empty());

        promoService.thousandthCustomer();
        Mockito.verify(randoms, Mockito.atLeastOnce()).getOptionalProduct();
        Assertions.assertFalse(randoms.getOptionalProduct().isPresent());
    }

    @Test
    void randomExchangeOrReturnYours_exchange() {
        Mockito.when(randoms.getOptionalProduct())
                .thenReturn(Optional.ofNullable(ProductFactory.createProduct(ProductType.values()[0])));
        Product yourProduct = ProductFactory.createProduct(ProductType.values()[1]);

        Product returnProduct = promoService.randomExchangeOrReturnYours(Optional.ofNullable(yourProduct));
        Assertions.assertNotEquals(yourProduct, returnProduct);
    }

    @Test
    void randomExchangeOrReturnYours_outOfStock() {
        Mockito.when(randoms.getOptionalProduct())
                .thenReturn(Optional.empty());
        Product yourProduct = ProductFactory.createProduct(ProductType.values()[1]);

        Product returnProduct = promoService.randomExchangeOrReturnYours(Optional.ofNullable(yourProduct));
        Assertions.assertEquals(yourProduct, returnProduct);
    }

    @Test
    void randomExchangeOrReturnYours_emptyInput() {
        Assertions.assertThrows(RuntimeException.class,
                () -> promoService.randomExchangeOrReturnYours(Optional.empty()));
    }

    @Test
    void discountCardOrProduct_product() {
        Mockito.when(randoms.getOptionalProduct())
                .thenReturn(Optional.ofNullable(ProductFactory.createProduct(ProductType.values()[0])));

        Product result = promoService.discountCardOrProduct();
        Mockito.verify(randoms, Mockito.atLeastOnce()).getOptionalProduct();
        Assertions.assertNotEquals(ProductType.DISCOUNTCARD, result.getType());
    }

    @Test
    void discountCardOrProduct_card() {
        Mockito.when(randoms.getOptionalProduct())
                .thenReturn(Optional.empty());

        Product result = promoService.discountCardOrProduct();
        Mockito.verify(randoms, Mockito.atLeastOnce()).getOptionalProduct();
        Assertions.assertEquals(ProductType.DISCOUNTCARD, result.getType());
    }

    @Test
    void guessTheNumber_guess() {
        int range = 1;
        int answer = 1;
        Mockito.when(randoms.nextInt(range))
                .thenReturn(answer);
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional
                .ofNullable(ProductFactory
                        .createProduct(ProductType.values()[0])));

        promoService.guessTheNumber(range, answer);
        Mockito.verify(randoms, Mockito.atLeastOnce()).nextInt(range);
        Assertions.assertTrue(randoms.getOptionalProduct().isPresent());
        Assertions.assertEquals(answer,randoms.nextInt(range));
    }

    @Test
    void guessTheNumber_notGuess() {
        int range = 2;
        int answer = 1;
        Mockito.when(randoms.nextInt(range))
                .thenReturn(range);
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional
                .ofNullable(ProductFactory
                        .createProduct(ProductType.values()[0])));

        promoService.guessTheNumber(range, answer);
        Mockito.verify(randoms, Mockito.atLeastOnce()).nextInt(range);
        Assertions.assertNotEquals(answer,randoms.nextInt(range));
    }

    @Test
    void guessTheNumber_guess_outOfStock() {
        int range = 1;
        int answer = 1;
        Mockito.when(randoms.nextInt(range))
                .thenReturn(answer);
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional.empty());

        promoService.guessTheNumber(range, answer);
        Mockito.verify(randoms, Mockito.atLeastOnce()).nextInt(range);
        Assertions.assertTrue(randoms.getOptionalProduct().isEmpty());
    }

    @Test
    void discount50PercentOnOldCollection_getDiscount() {
        int discount = 50;
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional
                .ofNullable(ProductFactory
                        .createProduct(ProductType.values()[0])));

        Assertions.assertEquals(discount, promoService.discount50PercentOnOldCollection());
    }

    @Test
    void discount50PercentOnOldCollection_noDiscount() {
        int discount = 50;
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional.empty());

        Assertions.assertNotEquals(discount, promoService.discount50PercentOnOldCollection());
    }

    @Test
    void getYourDiscount() {
        double maxDiscount = 0.3;
        Product testProduct = ProductFactory.createProduct(ProductType.values()[0]);
        double price = testProduct.getPrice();
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional
                .of(testProduct));
        Mockito.when(randoms.nextDiscount(maxDiscount)).thenReturn(maxDiscount);

        Product discountingProduct = promoService.getYourDiscount(randoms.getOptionalProduct().get(), maxDiscount);
        Assertions.assertEquals(price*maxDiscount, discountingProduct.getPrice());
    }

    @Test
    void getYourDiscount_exception() {
        double maxDiscount = 0.3;
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->promoService.getYourDiscount(randoms.getOptionalProduct().get(), maxDiscount));
    }

    @Test
    void isDiscount() {
        Product product = ProductFactory.createProduct(ProductType.values()[0]);
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional
                .ofNullable(ProductFactory
                        .createProduct(ProductType.values()[0])));

        Assertions.assertTrue(promoService.isDiscount(product));
    }

    @Test
    void isDiscount_isNot() {
        Product product = ProductFactory.createProduct(ProductType.values()[0]);
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional.empty());

        Assertions.assertFalse(promoService.isDiscount(product));
    }

    @Test
    void winWinLottery_firstWay() {
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional
                .ofNullable(ProductFactory
                        .createProduct(ProductType.values()[0])));

        Assertions.assertTrue(promoService.winWinLottery().isPresent());
        Mockito.verify(randoms,Mockito.atLeastOnce()).getOptionalProduct();
    }

    @Test
    void winWinLottery_secondWay() {
        Mockito.when(randoms.getOptionalProduct()).thenReturn(Optional.empty());

        Assertions.assertTrue(promoService.winWinLottery().isPresent());
        Mockito.verify(randoms,Mockito.atLeastOnce()).getOptionalProduct();
    }
}
