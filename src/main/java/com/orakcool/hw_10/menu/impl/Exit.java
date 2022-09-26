package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;

public class Exit implements Item {
    @Override
    public void run() {
        System.out.println("Good bye.");
    }
}
