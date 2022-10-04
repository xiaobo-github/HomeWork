package com.orakcool.hw_10.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubProduct {
    private String info;
    private String id;

    public SubProduct(String info, String id) {
        this.info = info;
        this.id = id;
    }

    @Override
    public String toString() {
        return info;
    }
}
