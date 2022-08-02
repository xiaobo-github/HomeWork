package com.orakcool.hw_10.app;

import com.orakcool.hw_10.Main;
import com.orakcool.hw_10.model.Product;
import com.orakcool.hw_10.model.ProductType;
import com.orakcool.hw_10.repository.impl.ElecricScooterRepository;
import com.orakcool.hw_10.repository.impl.LaptopRepository;
import com.orakcool.hw_10.repository.impl.PhoneRepository;
import com.orakcool.hw_10.service.ProductFactory;
import com.orakcool.hw_10.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Random;

public class CRUD {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final HashMap<ProductType, ProductService> myServices = new HashMap<>();

    public static void run() {
        myServices.put(ProductType.PHONE, new ProductService(new PhoneRepository()));
        myServices.put(ProductType.LAPTOP, new ProductService(new LaptopRepository()));
        myServices.put(ProductType.ELECTRICSCOOTER, new ProductService(new ElecricScooterRepository()));

        for (int i = 0; i < 2; i++) {
            myServices.get(ProductType.PHONE).add(ProductFactory.createProduct(ProductType.PHONE));
            myServices.get(ProductType.LAPTOP).add(ProductFactory.createProduct(ProductType.LAPTOP));
            myServices.get(ProductType.ELECTRICSCOOTER).add(ProductFactory.createProduct(ProductType.ELECTRICSCOOTER));
        }

        myServices.forEach((productType, service) -> LOG.info("Create products: {}", service.getAll()));

        printAll();

        Random random = new Random();
        ProductType randomProductType = ProductType.values()[random.nextInt(ProductType.values().length-1)];
        int randomIndex = random.nextInt(myServices.get(randomProductType).getAll().size());
        Product randomProduct =(Product) myServices.get(randomProductType).getAll().get(randomIndex);

        LOG.info("Update product: {}", randomProduct);
        randomProduct.setPrice(753.5);
        myServices.get(randomProductType).update(randomProduct);
        System.out.printf("Price now set to: %s%n", randomProduct.getPrice());

        printAll();

        LOG.info("Delete product: {}", randomProduct);
        myServices.get(randomProductType).delete(randomProduct.getId());
        System.out.printf("product with id: %s deleted%n", randomProduct.getId());

        printAll();
    }

    private static void printAll(){
        System.out.println("--------------------------------");
        myServices.forEach((productType, service) -> service.printAll());
        System.out.println("--------------------------------");
    }

}
