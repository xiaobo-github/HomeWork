package com.orakcool.hw_10.repository.impl;

import com.orakcool.hw_10.model.Manufacturer;
import com.orakcool.hw_10.model.products.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

class PhoneRepositoryTest {

    private PhoneRepository target;
    Phone testPhone;

    @BeforeEach
    void setUp() {
        target = new PhoneRepository();
        testPhone = randomPhone();
    }

    @Test
    void save() {
        target.save(testPhone);
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1,phones.size());
        Assertions.assertEquals(testPhone.getId(),phones.get(0).getId());
    }

    @Test
    void saveAll_singlePhone() {
        target.saveAll(Collections.singletonList(testPhone));
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1,phones.size());
        Assertions.assertEquals(testPhone.getId(),phones.get(0).getId());
    }

    @Test
    void saveAll_noPhone() {
        target.saveAll(Collections.emptyList());
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(0,phones.size());
    }

    @Test
    void saveAll_somePhones() {
        target.saveAll(List.of(testPhone, testPhone));
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(2,phones.size());
        Assertions.assertEquals(testPhone.getId(),phones.get(0).getId());
        Assertions.assertEquals(testPhone.getId(),phones.get(1).getId());
    }

    @Test
    void update() {
        final int newCount = 50;
        target.save(testPhone);
        testPhone.setCount(newCount);
        boolean result = target.update(testPhone);

        Assertions.assertTrue(result);
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1,phones.size());
        Assertions.assertEquals(newCount,phones.get(0).getCount());
        Assertions.assertEquals(testPhone.getId(),phones.get(0).getId());
    }

    @Test
    void update_noPhone() {
        target.save(testPhone);
        Phone testPhone2 = randomPhone();
        boolean result = target.update(testPhone2);

        Assertions.assertFalse(result);
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1,phones.size());
        Assertions.assertEquals(testPhone.getCount(),phones.get(0).getCount());
        Assertions.assertEquals(testPhone.getId(),phones.get(0).getId());
    }

    @Test
    void delete() {
        target.save(testPhone);
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1,phones.size());
        final boolean result = target.delete(phones.get(0).getId());
        Assertions.assertTrue(result);
        Assertions.assertEquals(0,target.getAll().size());
    }

    @Test
    void delete_wrongIdPhone() {
        target.save(testPhone);
        final boolean result = target.delete("321notRealId123");
        Assertions.assertFalse(result);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }

    @Test
    void getAll() {
        target.save(testPhone);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1,actualResult.size());
    }

    @Test
    void getAll_noPhone() {
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(0,actualResult.size());
    }

    @Test
    void findById() {
        target.save(testPhone);
        final String id = testPhone.getId();
        Assertions.assertTrue(target.findById(id).isPresent());
        Assertions.assertEquals(testPhone.getId(),target.findById(id).get().getId());
    }

    @Test
    void findById_wrongId() {
        target.save(testPhone);
        final String id = "Actually it's not an id";
        Assertions.assertEquals(Optional.empty(),target.findById(id));
    }

    Phone randomPhone(){
        Random random = new Random();
        Manufacturer[] manufactures = Manufacturer.values();
        return new Phone(
                "Title-" + random.nextInt(1000),
                random.nextInt(500),
                random.nextDouble(1000.0),
                "Model-" + random.nextInt(10),
                manufactures[random.nextInt(manufactures.length)]
        );
    }
}