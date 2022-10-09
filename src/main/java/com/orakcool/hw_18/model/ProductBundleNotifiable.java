package com.orakcool.hw_18.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProductBundleNotifiable extends Product {
    private final int amount;

    @Override
    public String toString() {
        return super.getBasicInfo() +
                ", amountInBundle=" + amount +
                '}';
    }
}
