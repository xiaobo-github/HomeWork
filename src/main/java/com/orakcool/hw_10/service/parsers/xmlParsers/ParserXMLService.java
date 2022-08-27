package com.orakcool.hw_10.service.parsers.xmlParsers;

import com.orakcool.hw_10.model.ParseModel;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.service.parsers.Parsers;
import com.orakcool.hw_10.util.ParseXML;

import java.util.HashMap;

import static com.orakcool.hw_10.util.ParseXML.*;

public class ParserXMLService <T extends Product> {

    public T parse(String source) {
        String tag = matchTag(source, START_TAG);

        try {
            String productSours = matchTag(source, FIND_TAG, tag);
            Parsers parser = Parsers.valueOf(tag.toUpperCase().trim());

            HashMap<String, ParseModel> model = ParseXML.matchModel(productSours);
            return parser.parse(model);
        }catch (IllegalArgumentException E){
            System.out.println("'"+tag+"' not found \n"+E);
        }

        return null;
    }
}
