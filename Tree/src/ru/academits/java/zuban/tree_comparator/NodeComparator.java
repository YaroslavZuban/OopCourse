package ru.academits.java.zuban.tree_comparator;

import ru.academits.java.zuban.tree.Node;

import java.util.Comparator;

public class NodeComparator<T extends Comparable<T>> implements Comparator<Node<T>> {
    @Override
    public int compare(Node<T> o1, Node<T> o2) {
        if (o1.getValue() == null && o2.getValue() == null) {
            return 0;
        } else if (o1.getValue() == null) {
            return -1;
        } else if (o2.getValue() == null) {
            return 1;
        }

        return o1.getValue().compareTo(o2.getValue());
    }
}