package ru.academits.java.zuban.tree;

public class Node<E> {
    private E value;
    private Node<E> left;
    private Node<E> right;

    public Node(E value) {
        this.value = value;
    }

    public Node(E value, Node<E> left, Node<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public void setValue(E value) {
        this.value = value;
    }
}