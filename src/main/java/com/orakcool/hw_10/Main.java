package com.orakcool.hw_10;

import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.service.*;
import com.orakcool.hw_10.service.services.ElectricScooterService;
import com.orakcool.hw_10.service.services.LaptopService;
import com.orakcool.hw_10.service.services.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Random;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final HashMap<ProductType,Service> myServices = new HashMap<>();

    public static void main(String[] args) {

        myServices.put(ProductType.PHONE,new PhoneService());
        myServices.put(ProductType.LAPTOP,new LaptopService());
        myServices.put(ProductType.ELECTRICSCOOTER,new ElectricScooterService());

        for (int i = 0; i < 3; i++) {
            myServices.get(ProductType.PHONE).add(ProductFactory.createProduct(ProductType.PHONE));
            myServices.get(ProductType.LAPTOP).add(ProductFactory.createProduct(ProductType.LAPTOP));
            myServices.get(ProductType.ELECTRICSCOOTER).add(ProductFactory.createProduct(ProductType.ELECTRICSCOOTER));
        }
        myServices.forEach((productType, service) -> LOG.info("Create products: " + service.getAll()));
        System.out.println("--------------------------------");
        myServices.forEach((productType, service) -> service.printAll());
        System.out.println("--------------------------------");

        Random random = new Random();
        ProductType randomProductType = ProductType.values()[random.nextInt(ProductType.values().length)];
        int randomIndex = random.nextInt(myServices.get(randomProductType).getAll().size());
        String id = myServices.get(randomProductType).getAll().get(randomIndex).getId();

        LOG.info("Update product: " + myServices.get(randomProductType).findById(id).get());
        myServices.get(randomProductType).findById(id).get().setPrice(753.5);

        System.out.println("Price now set to: " + myServices.get(randomProductType).findById(id).get().getPrice());
        System.out.println("--------------------------------");
        myServices.forEach((productType, service) -> service.printAll());
        System.out.println("--------------------------------");

        LOG.info("Delete product: " + myServices.get(randomProductType).findById(id).get());
        myServices.get(randomProductType).delete(id);

        System.out.println("product is deleted: " + myServices.get(randomProductType).findById(id).isEmpty());
        System.out.println("--------------------------------");
        myServices.forEach((productType, service) -> service.printAll());
        System.out.println("--------------------------------");
    }
}
