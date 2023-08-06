package ru.academits.java.zuban.singly_linked_list;

public class SinglyLinkedList<E> {
    private int size;
    private Node<E> head;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Список пуст.");
        }

        return head.getValue();
    }

    public E get(int index) {
        checkIndex(index);

        if (index == 0) {
            return getFirst();
        }

        Node<E> nodeCurrent = iteratingNodeByIndex(head, index);

        return nodeCurrent.getValue();
    }

    public E set(int index, E value) {
        checkIndex(index);

        Node<E> nodeCurrent = iteratingNodeByIndex(head, index);

        Node<E> result = new Node<>(nodeCurrent.getValue());
        nodeCurrent.setValue(value);

        return result.getValue();
    }

    public E deleteFirstValue() {
        Node<E> result = head;
        head = head.getNext();

        return result.getValue();
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        size--;

        if (index == 0) {
            return deleteFirstValue();
        }

        Node<E> nodeCurrent = iteratingNodeByIndex(head, index);

        Node<E> oldNode = nodeCurrent.getNext();
        nodeCurrent.setNext(nodeCurrent.getNext().getNext());

        return oldNode.getValue();
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

        Node<E> nodeCurrent = iteratingNodeByIndex(head, index - 1);
        nodeCurrent.setNext(new Node<>(value, nodeCurrent.getNext()));

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

        Node<E> nodeCurrent = head;

        while (nodeCurrent.getNext() != null) {
            if (nodeCurrent.getNext().getValue() == null || !nodeCurrent.getNext().getValue().equals(value)) {
                nodeCurrent = nodeCurrent.getNext();
            } else {
                nodeCurrent.setNext(nodeCurrent.getNext().getNext());
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

        Node<E> nodeCurrent = head;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(nodeCurrent.getValue()).append(", ");
            nodeCurrent = nodeCurrent.getNext();
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
        Node<E> nodeCurrent = head;

        while (nodeCurrent != null) {
            Node<E> nextNode = nodeCurrent.getNext();
            nodeCurrent.setNext(nodePrevious);
            nodePrevious = nodeCurrent;
            nodeCurrent = nextNode;
        }

        head = nodePrevious;
    }

    public SinglyLinkedList<E> copy() {
        if (size <= 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> singlyLinkedList = new SinglyLinkedList<>();

        Node<E> nodeCurrent = head;
        Node<E> nodePrevCopy = null;

        while (nodeCurrent != null) {
            Node<E> copiedNode = new Node<>(nodeCurrent.getValue());

            if (nodePrevCopy == null) {
                singlyLinkedList.head = copiedNode;
            } else {
                nodePrevCopy.setNext(copiedNode);
            }

            nodePrevCopy = copiedNode;
            nodeCurrent = nodeCurrent.getNext();
        }

        singlyLinkedList.size = size;

        return singlyLinkedList;
    }

    private Node<E> iteratingNodeByIndex(Node<E> node, int index) {
        Node<E> nodeCurrent = node;

        for (int i = 0; i < index; i++) {
            nodeCurrent = nodeCurrent.getNext();
        }

        return nodeCurrent;
    }
}