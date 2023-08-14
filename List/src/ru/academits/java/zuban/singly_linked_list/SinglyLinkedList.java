package ru.academits.java.zuban.singly_linked_list;

import java.util.NoSuchElementException;

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

        if (index == 0) {
            return getFirst();
        }

        Node<E> node = getNode(index);

        return node.getValue();
    }

    public E setNode(int index, E value) {
        checkIndex(index);

        Node<E> node = getNode(index);

        E oldValueNode = node.getValue();
        node.setValue(value);

        return oldValueNode;
    }

    public E deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        E deleteValuedNode  = head.getValue();
        head = head.getNext();

        return deleteValuedNode;
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        size--;

        if (index == 0) {
            return deleteFirst();
        }

        Node<E> node = getNode(index);

        E deleteValuedNode = node.getValue();
        node.setNext(node.getNext().getNext());

        return deleteValuedNode;
    }

    public E setFirst(E value) {
        size++;

        if (head == null) {
            head = new Node<>(value);
            return null;
        }

        head = new Node<>(value, head);

        return head.getNext().getValue();
    }

    public void insert(int index, E value) {
        checkIndex(index);

        if (index == 0) {
            setFirst(value);
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
        if (head.getValue() == null || head.getValue().equals(value)) {
            head = head.getNext();
            size--;

            return true;
        }

        Node<E> node = head;

        while (node.getNext() != null) {
            if (node.getNext().getValue() == null || !node.getNext().getValue().equals(value)) {
                node = node.getNext();
            } else {
                node.setNext(node.getNext().getNext());
                size--;
                return true;
            }
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

        for (int i = 0; i < size; i++) {
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

        Node<E> nodePrevious = null;
        Node<E> node = head;

        while (node != null) {
            Node<E> nextNode = node.getNext();
            node.setNext(nodePrevious);
            nodePrevious = node;
            node = nextNode;
        }

        head = nodePrevious;
    }

    public SinglyLinkedList<E> copy() {
        if (size <= 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> singlyLinkedList = new SinglyLinkedList<>();

        Node<E> node = head;
        Node<E> nodePrevCopy = null;

        while (node != null) {
            Node<E> copiedNode = new Node<>(node.getValue());

            if (nodePrevCopy == null) {
                singlyLinkedList.head = copiedNode;
            } else {
                nodePrevCopy.setNext(copiedNode);
            }

            nodePrevCopy = copiedNode;
            node = node.getNext();
        }

        singlyLinkedList.size = size;

        return singlyLinkedList;
    }

    private Node<E> getNode(int index) {
        Node<E> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }
}