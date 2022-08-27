package com.orakcool.hw_10.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Downloader {
    @SneakyThrows
    public static @NotNull String load(String path){
        String ln = "\n";
        StringBuilder result = new StringBuilder();
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path);

        assert inputStream != null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append(ln);
            }
        }

        return result.toString();
    }
}
