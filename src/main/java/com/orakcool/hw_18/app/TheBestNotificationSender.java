package com.orakcool.hw_18.app;

import com.orakcool.hw_18.service.ProductService;

public class TheBestNotificationSender {
    public static void run() {
        ProductService.createProduct(7);

        System.out.printf(" %s\n notifications sent: %d", ProductService.getAll(), ProductService.sendNotifications());
    }
}
