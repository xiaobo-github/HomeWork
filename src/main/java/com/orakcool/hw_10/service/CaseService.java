package com.orakcool.hw_10.service;

import com.orakcool.hw_10.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@Getter
@Setter
public class CaseService<T extends Product> {
    private final T casesProduct;

    public CaseService(T casesProduct) {
        this.casesProduct = casesProduct;
    }

    public String showInside() {
        return casesProduct.toString();
    }

    public void getYourDiscount() {
        double minDiscount = 10;
        double maxDiscount = 30;
        casesProduct.setPrice(discountPrice(casesProduct.getPrice(), minDiscount, maxDiscount));
    }

    public <E extends Number> void add(@NotNull E count) {
        casesProduct.setCount(casesProduct.getCount() + count.intValue());
    }

    private double discountPrice(double price, double minDiscount, double maxDiscount) {
        Random random = new Random();
        double discount = maxDiscount - minDiscount;
        return price * (minDiscount + random.nextDouble(discount));
    }
}
