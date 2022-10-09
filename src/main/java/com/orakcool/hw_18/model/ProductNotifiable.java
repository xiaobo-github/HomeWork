package com.orakcool.hw_18.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProductNotifiable extends Product {
    public String generateAddressForNotification() {
        return "somerandommail@gmail.com";
    }
}
