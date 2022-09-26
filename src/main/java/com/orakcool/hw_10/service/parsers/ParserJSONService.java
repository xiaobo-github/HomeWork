package com.orakcool.hw_10.service.parsers;

import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.util.ParseJSON;

public class ParserJSONService <T extends Product> {
    public Phone parse(String source) {
        Parsers parser = Parsers.PHONES;

        return parser.parse(ParseJSON.getModel(source));
    }
}
