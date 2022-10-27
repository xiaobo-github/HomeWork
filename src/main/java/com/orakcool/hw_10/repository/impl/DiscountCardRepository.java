package com.orakcool.hw_10.repository.impl;

import com.orakcool.hw_10.model.products.DiscountCard;
import com.orakcool.hw_10.repository.AbstractCRUDRepository;
import com.orakcool.hw_10.annotations.Singleton;

import java.util.*;

@Singleton
public class DiscountCardRepository implements AbstractCRUDRepository<DiscountCard> {
    private final List<DiscountCard> cards;

    public DiscountCardRepository() {
        cards = new LinkedList<>();
    }

    @Override
    public void save(DiscountCard card) {
        cards.add(card);
    }

    @Override
    public void saveAll(List<DiscountCard> cards) {
        for (DiscountCard card : cards) {
            save(card);
        }
    }

    @Override
    public boolean update(DiscountCard card) {
        final Optional<DiscountCard> result = findById(card.getId());
        if (result.isEmpty()) {
            return false;
        }
        final DiscountCard originCard = result.get();
        CardCopy.copy(card, originCard);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<DiscountCard> iterator = cards.iterator();
        while (iterator.hasNext()) {
            final DiscountCard card = iterator.next();
            if (card.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DiscountCard> getAll() {
        if (cards.isEmpty()) {
            return Collections.emptyList();
        }
        return cards;
    }

    @Override
    public Optional<DiscountCard> findById(String id) {
        DiscountCard result = null;
        for (DiscountCard card : cards) {
            if (card.getId().equals(id)) {
                result = card;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class CardCopy {
        private static void copy(final DiscountCard from, final DiscountCard to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
