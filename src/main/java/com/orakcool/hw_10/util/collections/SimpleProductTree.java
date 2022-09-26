package com.orakcool.hw_10.util.collections;

import com.orakcool.hw_10.model.Product;
import lombok.Getter;
import lombok.Setter;

public class SimpleProductTree<T extends Product> {
    private final Node<T> node;
    final ProductComparator<T> comparator;

    public SimpleProductTree() {
        node = new Node<>(null);
        comparator = new ProductComparator<>();
    }

    public void add(T product) {
        node.add(product, comparator);
    }

    @Override
    public String toString() {
        return node.toString();
    }

    public String costToString() {
        return node.costToString(new StringBuilder(), true, new StringBuilder());
    }

    @Getter
    @Setter
    class Node<T extends Product> {
        private Node<T> right;
        private Node<T> left;
        private T product;

        Node(T product) {
            this.product = product;
            if (product != null) {
                right = new Node<T>(null);
                left = new Node<T>(null);
            }
        }

        void add(T product, ProductComparator<T> comparator) {
            if (this.product == null) {
                this.product = product;
                right = new Node<T>(null);
                left = new Node<T>(null);
            } else {
                int compare = comparator.compare(this.product, product);
                if (compare < 0) {
                    left.add(product, comparator);
                } else if (compare > 0) {
                    right.add(product, comparator);
                }
            }
        }

        private double cost() {
            double cost = product.getPrice();

            if (left.product != null) {
                cost += left.cost();
            }

            if (right.product != null) {
                cost += right.cost();
            }

            return cost;
        }

        public String costToString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if (right.product != null) {
                right.costToString(new StringBuilder().append(prefix).append(isTail ? "|     " : "      "), false, sb);
            }

            double cost = cost();
            sb.append(prefix).append(isTail ? "\\--- " : "/--- ").append(String.format("%6.2f", cost)).append("\n");
            if (left.product != null) {
                left.costToString(new StringBuilder().append(prefix).append(isTail ? "      " : "|     "), true, sb);
            }
            return sb.toString();
        }

        public StringBuilder toString2(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if (right.product != null) {
                right.toString2(new StringBuilder().append(prefix).append(isTail ? "|       " : "        "), false, sb);
            }

            sb.append(prefix).append("/ ").append(product.getTitle()).append("\n");
            sb.append(prefix).append("| ").append(String.format("%6.2f", product.getPrice())).append("\n");
            sb.append(prefix).append("\\ ").append(product.getType().name()).append("\n");
            if (left.product != null) {
                left.toString2(new StringBuilder().append(prefix).append(isTail ? "      " : "|     "), true, sb);
            }
            return sb;
        }

        @Override
        public String toString() {
            return this.toString2(new StringBuilder(), true, new StringBuilder()).toString();
        }
    }
}
