package com.orakcool.hw_18.util;

import com.orakcool.hw_18.model.Product;
import com.orakcool.hw_18.model.ProductBundleNotifiable;
import com.orakcool.hw_18.model.ProductNotifiable;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class ProductFactory {
    public static Product generateRandomProduct() {
        Random random = new Random();

        if (random.nextBoolean()) {
            return ProductBundleNotifiable.builder()
                    .amount(random.nextInt(15))
                    .available(random.nextBoolean())
                    .channel(random.nextBoolean() + "" + random.nextDouble())
                    .price(random.nextDouble())
                    .id(random.nextLong())
                    .title(random.nextFloat() + "" + random.nextDouble())
                    .build();
        } else {
            return ProductNotifiable.builder()
                    .id(random.nextLong())
                    .title(random.nextFloat() + "" + random.nextDouble())
                    .available(random.nextBoolean())
                    .channel(random.nextBoolean() + "" + random.nextDouble())
                    .price(random.nextDouble())
                    .build();
        }
    }
}
