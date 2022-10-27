package com.orakcool.hw_10.util.collections;

import com.orakcool.hw_10.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

public class ProductLinkedList<T extends Product> implements Iterable<T> {

    transient private int size = 0;
    transient private Node<T> first;
    transient private Node<T> last;

    /**
     * @param version product version to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(int version) {
        int index = 0;
        for (Node<T> node = first; node != null; node = node.getNext()) {
            if (version == node.getVersion()) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void remove(int version) {
        Optional.ofNullable(find(version)).ifPresent(this::remove);
    }

    public void remove(@NotNull Node<T> node) {
        if (node.hasPrev()) {
            if (node.hasNext()) {
                node.getPrev().setNext(node.getNext());
            } else {
                last = node.getPrev();
                node.getPrev().setNext(null);
            }
        } else if (node.hasNext()) {
            first = node.getNext();
            node.getNext().setPrev(null);
        } else {
            first = null;
        }
    }

    public void addToHead(T product, int version) {
        if (first == null) {
            add(product, version);
        } else {
            first = new Node<>(version, product, null, first);
        }
    }

    public void change(T product, int version) {
        Optional.ofNullable(find(version)).ifPresent(node -> node.setProduct(product));
    }

    public void add(T product, int version) {
        if (size == 0) {
            first = new Node<>(version, product, null, null);
            last = first;
        } else {
            Node<T> newPrev = last;
            last = new Node<>(version, product, newPrev, null);
            newPrev.setNext(last);
        }
        size++;
    }

    private Node<T> find(int version) {
        for (Node<T> node = first; node != null; node = node.getNext()) {
            if (version == node.getVersion()) {
                return node;
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ProductListIterator();
    }

    public int getNumberOfVersions() {
        return size;
    }

    public LocalDateTime getFirstVersionDate() {
        return first.getDate();
    }

    public LocalDateTime getLastVersionDate() {
        return last.getDate();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    class ProductListIterator implements Iterator<T> {

        ProductLinkedList<T>.Node<T> node;

        ProductListIterator() {
            node = first;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T product = node.getProduct();
            node = node.getNext();
            return product;
        }
    }

    @Getter
    @Setter
    class Node<T> {
        private int version;
        private LocalDateTime date;
        private T product;
        private Node<T> next;
        private Node<T> prev;

        public boolean hasPrev() {
            return prev != null;
        }

        public boolean hasNext() {
            return next != null;
        }

        Node(int version, T product, Node<T> prev, Node<T> next) {
            this.version = version;
            this.date = LocalDateTime.now();
            this.product = product;
            this.prev = prev;
            this.next = next;
        }
    }
}
