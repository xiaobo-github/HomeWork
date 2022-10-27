package com.orakcool.hw_10.app;

import com.orakcool.hw_10.menu.Items;
import com.orakcool.hw_10.util.Reader;

public class App {
    private static final String TITLE = "Main Menu:";

    public static void run() {
        menu();
    }

    private static void menu() {
        boolean isExit = true;
        do {
            int item = Reader.menu(TITLE, Items.values(), true);
            isExit = Items.values()[item].run();
        } while (!isExit);
    }
}

