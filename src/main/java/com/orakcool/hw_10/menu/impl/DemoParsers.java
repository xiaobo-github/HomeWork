package com.orakcool.hw_10.menu.impl;

import com.orakcool.hw_10.menu.Item;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.service.parsers.ParserJSONService;
import com.orakcool.hw_10.service.parsers.ParserXMLService;
import com.orakcool.hw_10.util.Downloader;

public class DemoParsers implements Item {

    @Override
    public void run() {
        ParserXMLService<Phone> xmlParser = new ParserXMLService<>();
        Phone phone = xmlParser.parse(Downloader.load("phone.xml"));
        System.out.println("From XML:");
        System.out.println("phone = " + phone);
        System.out.println("----------");

        ParserJSONService<Phone> jsonParser = new ParserJSONService<>();
        phone = jsonParser.parse(Downloader.load("phone.json"));
        System.out.println("From JSON:");
        System.out.println("phone = " + phone);
        System.out.println();
    }
}
