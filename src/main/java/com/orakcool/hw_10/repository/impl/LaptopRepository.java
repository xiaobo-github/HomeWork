package com.orakcool.hw_10.repository.impl;

import com.orakcool.hw_10.model.products.Laptop;
import com.orakcool.hw_10.repository.LaptopCrudRepository;

import java.util.*;
public class LaptopRepository implements LaptopCrudRepository {
    private final List<Laptop> laptops;

    public LaptopRepository() {
        laptops = new LinkedList<>();
    }

    @Override
    public void save(Laptop laptop) {
        laptops.add(laptop);
    }

    @Override
    public void saveAll(List<Laptop> laptops) {
        for (Laptop laptop : laptops) {
            save(laptop);
        }
    }

    @Override
    public boolean update(Laptop laptop) {
        final Optional<Laptop> result = findById(laptop.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Laptop originLaptop = result.get();
        LaptopRepository.LaptopCopy.copy(laptop, originLaptop);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Laptop> iterator = laptops.iterator();
        while (iterator.hasNext()) {
            final Laptop laptop = iterator.next();
            if (laptop.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Laptop> getAll() {
        if (laptops.isEmpty()) {
            return Collections.emptyList();
        }
        return laptops;
    }

    @Override
    public Optional<Laptop> findById(String id) {
        Laptop result = null;
        for (Laptop laptop : laptops) {
            if (laptop.getId().equals(id)) {
                result = laptop;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class LaptopCopy {
        private static void copy(final Laptop from, final Laptop to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
