package com.orakcool.hw_10.service.parsers;

import com.orakcool.hw_10.model.ParseModel;
import com.orakcool.hw_10.model.Product;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum Parsers {
    PHONES(new PhoneParser());

    private final Parser parser;

    Parsers(PhoneParser parser) {
        this.parser = parser;
    }

    public <T extends Product> T parse(HashMap<String, ParseModel> model) {
        return (T) parser.parse(model);
    }
}
