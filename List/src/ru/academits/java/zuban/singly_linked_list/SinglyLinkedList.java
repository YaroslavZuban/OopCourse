package ru.academits.java.zuban.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private int size;
    private Node<E> head;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getValue();
    }

    public E get(int index) {
        checkIndex(index);

        return getNode(index).getValue();
    }

    public E set(int index, E value) {
        checkIndex(index);

        Node<E> node = getNode(index);

        E oldValue = node.getValue();
        node.setValue(value);

        return oldValue;
    }

    public E deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        E deletedValue = head.getValue();
        head = head.getNext();

        size--;

        return deletedValue;
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        Node<E> previousNode = getNode(index - 1);

        E deletedValue = previousNode.getNext().getValue();

        previousNode.setNext(previousNode.getNext().getNext());
        size--;

        return deletedValue;
    }

    public void addFirst(E value) {
        size++;

        head = new Node<>(value, head);
    }

    public void insert(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + size + "]");
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        Node<E> previousNode = getNode(index - 1);
        previousNode.setNext(new Node<>(value, previousNode.getNext()));

        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + (size - 1) + "]");
        }
    }

    public boolean deleteByValue(E value) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(head.getValue(), value)) {
            deleteFirst();
            return true;
        }

        Node<E> node = head;

        while (node.getNext() != null) {
            if (Objects.equals(node.getNext().getValue(), value)) {
                node.setNext(node.getNext().getNext());
                size--;
                return true;
            }

            node = node.getNext();
        }

        return false;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        Node<E> node = head;

        while (node != null) {
            stringBuilder.append(node.getValue()).append(", ");
            node = node.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    public void reverse() {
        if (head == null) {
            return;
        }

        Node<E> previousNode = null;
        Node<E> currentNode = head;

        while (currentNode != null) {
            Node<E> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copiedList = new SinglyLinkedList<>();

        if (size == 0) {
            return copiedList;
        }

        copiedList.head = new Node<>(head.getValue());

        Node<E> copiedNode = null;

        for (Node<E> originalNode = head; originalNode != null; originalNode = originalNode.getNext()) {
            if (originalNode.equals(head)) {
                copiedNode = new Node<>(head.getValue());
            } else {
                copiedNode.setNext(new Node<>(originalNode.getValue()));
                copiedNode = new Node<>(originalNode.getValue());
            }
        }

        copiedList.size = size;

        return copiedList;
    }

    private Node<E> getNode(int index) {
        Node<E> node = head;

        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }

        return node;
    }
}