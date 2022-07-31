package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PromoService {
    private final List<Product> bonusAccount;
    private final RandomService randoms;

    PromoService(RandomService randoms){
        bonusAccount = new ArrayList<>();
        this.randoms = randoms;
    }

    public void thousandthCustomer(){
            randoms.getOptionalProduct()
                    .ifPresent(product -> {
                        System.out.println("You are the thousandth customer! Please, take your prize - "
                                + product.getType().toString()
                                + ". Congratulations!");
                        bonusAccount.add(product);
                    });
    }

    public Product randomExchangeOrReturnYours(Optional<Product> yourOldProduct){
        if(yourOldProduct.isPresent()) {
            System.out.print("You can exchange yours "
                    + yourOldProduct.get().getType().toString()
                    + " for a/an ");
            Product wonProduct = randoms.getOptionalProduct()
                    .orElse(yourOldProduct.get());
            System.out.println(wonProduct.toString());
            return wonProduct;
        }
        throw new RuntimeException("Product must be exist(not null)");
    }

    public Product discountCardOrProduct(){
        System.out.print("You won a/an ");
        Product wonProduct = randoms.getOptionalProduct()
                .orElseGet(() -> ProductFactory.createProduct(ProductType.DISCOUNTCARD));
        System.out.println(wonProduct.toString());
        return wonProduct;
    }

    public void guessTheNumber(int range, int userAnswer){
        String result = "";
        System.out.print("And... ");
        if (userAnswer == randoms.nextInt(range)){
            result = randoms.getOptionalProduct()
                    .map(product -> "You won a/an "+product.getType().toString())
                    .orElse("Sorry, all promotional items are sold out...");
        }else {
            result = "You did not guess right... Please try again later";
        }
        System.out.println(result);
    }

    public int discount50PercentOnOldCollection(){
        final int discount = 50;
        AtomicInteger result = new AtomicInteger();
        randoms.getOptionalProduct().ifPresentOrElse(
                (product) ->{
                    System.out.println("You can buy an old collection "
                            +product.getType().toString()
                            +" with a 50% discount");
                    result.set(discount);
                },
                () ->{
                    System.out.println("Unfortunately, we do not have products from the old collection.");
                }
        );
        return result.get();
    }

    public Product getYourDiscount(Product product,double maxDiscount){
        Product discountingProduct = Optional.ofNullable(product).orElseThrow(IllegalArgumentException::new);
        double price = discountingProduct.getPrice();
        discountingProduct.setPrice(price * randoms.nextDiscount(maxDiscount));
        return discountingProduct;
    }

    public boolean isDiscount(Product usersProduct){
        return Optional.ofNullable(usersProduct).filter(value -> randoms.getOptionalProduct()
                .filter(product -> product.getType() == value.getType())
                .isPresent()).isPresent();
    }

    public Optional<Product> winWinLottery(){
        return randoms.getOptionalProduct().or(() ->
                Optional.of(ProductFactory
                        .createProduct(ProductType.values()[0]))
        );
    }

    public Optional<List<Product>> getBonuses(){
        return Optional.ofNullable(bonusAccount);
    }
}
