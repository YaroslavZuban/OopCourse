package ru.academits.java.zuban.singlyLinkedList;

public class SinglyLinkedList<E> {
    private int size;
    private Node<E> head;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        return head.getValue();
    }

    public E get(int index) {
        validateIndex(index);

        if (index == 0) {
            return getFirst();
        }

        Node<E> result = head;

        for (int i = 0; i < size; i++) {
            if (i != index - 1) {
                result = head.getNext();
            } else {
                return result.getValue();
            }
        }

        return null;
    }

    public E set(int index, E value) {
        validateIndex(index);

        checkNotNull(value);

        Node<E> oldValue;
        Node<E> listCopy = head;

        for (int i = 0; i < size; i++) {
            if (i != index - 2) {
                listCopy = listCopy.getNext();
            } else {
                oldValue = listCopy.getNext();

                Node<E> valueInserted = new Node<>(value);

                listCopy.setNext(valueInserted);
                valueInserted.setNext(oldValue.getNext());

                return oldValue.getValue();
            }
        }

        return null;
    }

    public E deleteIndex(int index) {
        validateIndex(index);

        Node<E> oldNode;

        if (index == 0) {
            oldNode = head;
            head = head.getNext();
        } else if (index == 1) {
            oldNode = head;
            head = head.getNext().getNext();
        } else {
            Node<E> nodeCopy = head;

            for (int i = 1; i < index ; i++) {
                nodeCopy = nodeCopy.getNext();
            }

            oldNode = nodeCopy.getNext();
            nodeCopy.setNext(nodeCopy.getNext().getNext());
        }

        size--;
        return oldNode.getValue();
    }

    public E setFirst(E value) {
        checkNotNull(value);

        Node<E> head = new Node<>(value);

        if (this.head == null) {
            this.head = head;
            size++;
            return null;
        }


        head.setNext(this.head);
        this.head = head;
        size++;

        return head.getValue();
    }

    public void insert(int index, E value) {
        validateIndex(index);

        checkNotNull(value);

        if (index == 0) {
            setFirst(value);
            return;
        }

        Node<E> nodeCopy = head;

        for (int i = 0; i < size; i++) {
            if (i != index - 1) {
                nodeCopy = nodeCopy.getNext();
            } else {
                Node<E> valueInserted = new Node<>(value);

                valueInserted.setNext(nodeCopy.getNext());
                nodeCopy.setNext(valueInserted);

                size++;
                break;
            }
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + (size - 1) + "]");
        }
    }

    public boolean deleteValue(E value) {
        checkNotNull(value);

        if (head.getValue() != null && head.getValue().equals(value)) {
            head = head.getNext();
            size--;

            return true;
        }

        Node<E> nodeCopy = head;

        while (nodeCopy.getNext() != null) {
            if (!nodeCopy.getNext().getValue().equals(value)) {
                nodeCopy = nodeCopy.getNext();
            } else {
                nodeCopy.setNext(nodeCopy.getNext().getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    private void checkNotNull(E value) {
        if (value == null) {
            throw new NullPointerException("Переданный объект равен null");
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Список пуст!";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        Node<E> temp = head;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(temp.getValue()).append(", ");
            temp = temp.getNext();
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
        if (size <= 0) {
            return null;
        }

        SinglyLinkedList<E> singlyLinkedList = new SinglyLinkedList<>();
        Node<E> currentNode = head;

        while (currentNode != null) {
            singlyLinkedList.setFirst(currentNode.getValue());
            currentNode = currentNode.getNext();
        }

        singlyLinkedList.reverse();

        return singlyLinkedList;
    }
}