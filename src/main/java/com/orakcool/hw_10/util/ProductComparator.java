package com.orakcool.hw_10.util;

import com.orakcool.hw_10.model.Product;

import java.util.Comparator;

public class ProductComparator <T extends Product> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        double price = o1.getPrice() - o2.getPrice();
        if (price == 0){
            int titleCompare = o1.getTitle().compareTo(o2.getTitle());
            if (titleCompare == 0){
                return o1.getCount() - o2.getCount();
            }else {
                return titleCompare;
            }
        }
        return price > 0? 1 : -1;
    }
}
