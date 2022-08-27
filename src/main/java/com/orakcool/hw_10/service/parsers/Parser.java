package com.orakcool.hw_10.service.parsers;

import com.orakcool.hw_10.model.ParseModel;
import com.orakcool.hw_10.model.Product;

import java.util.HashMap;

public interface Parser<T extends Product> {
    public T parse(HashMap<String, ParseModel> model);
}
