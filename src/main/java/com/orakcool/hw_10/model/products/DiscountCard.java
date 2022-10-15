package com.orakcool.hw_10.model.products;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;

public class DiscountCard extends Product {
    public DiscountCard() {
        super("card", 1, 0, ProductType.DISCOUNTCARD);
    }

    @Override
    public String toString() {
        return "Discount_card{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
