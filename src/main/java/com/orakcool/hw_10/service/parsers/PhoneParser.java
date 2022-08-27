package com.orakcool.hw_10.service.parsers;

import com.orakcool.hw_10.model.ParseModel;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.model.products.Phone;
import com.orakcool.hw_10.service.ProductFactory;

import java.util.HashMap;

public class PhoneParser implements Parser<Phone> {
        @Override
        public Phone parse(HashMap<String, ParseModel> model){
            Phone phone = (Phone) ProductFactory.createProduct(ProductType.PHONE);

            phone.setTitle(model.get("title").getParameter());
            phone.setModel(model.get("model").getParameter());
            phone.setPrice(model.get("price").getDoubleParameter());
            phone.setPriceCurrency(model.get("priceCurrency").getParameter());
            phone.setManufacturer(model.get("manufacturer").getManufacturerParameter());
            phone.setCreated(model.get("created").getDataTimeParameter());
            phone.setCount(model.get("count").getIntParameter());
            phone.setOperatingSystem(model.get("operating-system").getOSParameter());

            return phone;
        }
}
