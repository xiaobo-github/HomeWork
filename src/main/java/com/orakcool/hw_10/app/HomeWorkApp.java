package com.orakcool.hw_10.app;

import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.service.parsers.jsonParsers.ParserJSONService;
import com.orakcool.hw_10.util.Downloader;
import com.orakcool.hw_10.service.parsers.xmlParsers.ParserXMLService;

public class HomeWorkApp {
    public static void run() {
        ParserXMLService<Phone> xmlParser = new ParserXMLService<>();
        Phone phone = xmlParser.parse(Downloader.load("phone.xml"));
        System.out.println("From XML:");
        System.out.println("phone = " + phone);
        System.out.println("----------");

        ParserJSONService<Phone> jsonParser = new ParserJSONService<>();
        phone = jsonParser.parse(Downloader.load("phone.json"));
        System.out.println("From JSON:");
        System.out.println("phone = " + phone);
        System.out.println("----------");

    }
}
