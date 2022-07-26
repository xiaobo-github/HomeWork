package com.orakcool.hw_10.repository.impl;

import com.orakcool.hw_10.model.products.ElectricScooter;
import com.orakcool.hw_10.repository.AbstractCRUDRepository;

import java.util.*;

public class ElecricScooterRepository implements AbstractCRUDRepository<ElectricScooter> {
    private final List<ElectricScooter> electricScooters;
    public ElecricScooterRepository() {
        electricScooters = new LinkedList<>();
    }

    @Override
    public void save(ElectricScooter electricScooter) {
        electricScooters.add(electricScooter);
    }

    @Override
    public void saveAll(List<ElectricScooter> electricScooters) {
        for (ElectricScooter electricScooter : electricScooters) {
            save(electricScooter);
        }
    }

    @Override
    public boolean update(ElectricScooter electricScooter) {
        final Optional<ElectricScooter> result = findById(electricScooter.getId());
        if (result.isEmpty()) {
            return false;
        }
        final ElectricScooter originElectricScooter = result.get();
        ElectricScooterCopy.copy(electricScooter, originElectricScooter);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<ElectricScooter> iterator = electricScooters.iterator();
        while (iterator.hasNext()) {
            final ElectricScooter electricScooter = iterator.next();
            if (electricScooter.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ElectricScooter> getAll() {
        if (electricScooters.isEmpty()) {
            return Collections.emptyList();
        }
        return electricScooters;
    }

    @Override
    public Optional<ElectricScooter> findById(String id) {
        ElectricScooter result = null;
        for (ElectricScooter electricScooter : electricScooters) {
            if (electricScooter.getId().equals(id)) {
                result = electricScooter;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class ElectricScooterCopy {
        private static void copy(final ElectricScooter from, final ElectricScooter to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
