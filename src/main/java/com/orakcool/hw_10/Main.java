package com.orakcool.hw_10;

import com.orakcool.hw_10.annotations.ContextProcessor;
import com.orakcool.hw_10.app.App;

public class Main {
    public static void main(String[] args) {
        ContextProcessor.run("com.orakcool.hw_10");
        App.run();
    }
}
