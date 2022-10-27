package com.orakcool.hw_10.util;

public class Menu {

    public enum EditMenuItems {
        COUNT("Count"),
        TITLE("Title"),
        PRICE("Price");

        private final String item;

        EditMenuItems(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }
    }
}
