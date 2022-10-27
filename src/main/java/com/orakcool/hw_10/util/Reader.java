package com.orakcool.hw_10.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class Reader {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String SEPARATOR = " - ";
    public static final int EXIT_FROM_MENU = 0;
    private static final String EXIT = EXIT_FROM_MENU + SEPARATOR + "Back to previous menu";
    public static final int SET_EXIT_TO_ZERO = 1;

    public static String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String readLine(String message) {
        System.out.println(message);
        return readLine();
    }

    public static int parseBoundsInt(String src, int min, int max) throws IndexOutOfBoundsException {
        int parsedInt = Integer.parseInt(src);
        if (parsedInt >= min && parsedInt < max)
            return parsedInt;
        else throw new IndexOutOfBoundsException();
    }

    public static int readInt(String message, int min, int max) {
        System.out.println(message);
        boolean doThis = false;
        do {
            doThis = false;
            try {
                return Reader.parseBoundsInt(Reader.readLine(), min, max);
            } catch (Exception e) {
                System.out.println("Invalid input, please make your choice and try again...");
                System.out.println(message);
                doThis = true;
            }
        } while (doThis);
        return 0;
    }

    public static int readInt(String message, int max) {
        return readInt(message, 0, max);
    }

    public static int readInt(String message) {
        return readInt(message, 0, Integer.MAX_VALUE);
    }

    public static Double readDouble(String message) {
        System.out.println(message);
        boolean doThis = false;
        do {
            doThis = false;
            try {
                return Double.parseDouble(Reader.readLine());
            } catch (Exception e) {
                System.out.println("Invalid input, please make your choice and try again...");
                System.out.println(message);
                doThis = true;
            }
        } while (doThis);
        return 0d;
    }

    public static <T> void readMenu(String title, T[] items, Consumer<Integer> work) {
        String menu = getMenuOptions(title, items, false);
        int userInput;
        do {
            userInput = Reader.readInt(menu, items.length + SET_EXIT_TO_ZERO);

            if (userInput != 0) {
                work.accept(userInput - SET_EXIT_TO_ZERO);
                System.out.println("Done!\n");
            }
        } while (userInput != 0);
    }

    public static String getMenuOptions(String question, Object[] items, boolean isMainMenu) {
        StringBuilder builder = new StringBuilder(question).append("\n");
        if (isMainMenu) {
            for (int i = SET_EXIT_TO_ZERO; i < items.length; i++) {
                builder.append(i).append(SEPARATOR).append(items[i]).append("\n");
            }
            builder.append(0).append(SEPARATOR).append(items[0]);
        } else {
            for (int i = SET_EXIT_TO_ZERO; i <= items.length; i++) {
                builder.append(i).append(SEPARATOR).append(items[i - SET_EXIT_TO_ZERO]).append("\n");
            }
            builder.append(EXIT);
        }
        return builder.toString();
    }

    public static int menu(String title, Object[] items, boolean isMainMenu) {
        String message = getMenuOptions(title, items, isMainMenu);
        return readInt(message, 0, items.length + SET_EXIT_TO_ZERO);
    }
}
