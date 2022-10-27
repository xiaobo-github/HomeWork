package com.orakcool.hw_10.menu;

import com.orakcool.hw_10.menu.impl.*;
import lombok.Getter;

@Getter
public enum Items {
    EXIT("Exit", new Exit(), true),
    CREATE("Create product", new Create()),
    SHOW("Show product", new Show()),
    EDIT("Edit product", new Edit()),
    DELETE("Delete product", new Delete()),
    LIST("Demo of a custom list of products", new DemoList()),
    PARSERS("Demo of json and xml parsers", new DemoParsers()),
    TREE("Demo of binary tree", new DemoTree()),
    STEAM_API("Demo of stream api", new DemoStreamAPI());

    private final boolean exitFromMenu;
    private final String message;
    private final Item item;

    Items(String message, Item item, boolean isExit) {
        this.message = message;
        this.item = item;
        this.exitFromMenu = isExit;
    }

    Items(String message, Item item) {
        this(message, item, false);
    }

    public boolean run() {
        item.run();
        return exitFromMenu;
    }

    @Override
    public String toString() {
        return message;
    }
}
