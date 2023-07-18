package ru.academits.java.zuban.list;

public class List<T> {
    private int size;
    private Node<T> list;

    public int getSize() {
        return size;
    }

    public Node<T> getOneElement() {
        return list;
    }

    public Node<T> getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 1) {
            return getOneElement();
        }

        int counter = 0;
        Node<T> result = list;

        for (int i = 0; i < size; i++) {
            if (counter != index - 1) {
                result = list.getNext();
            } else {
                break;
            }

            counter++;
        }

        return result;
    }

    public Node<T> setElement(int index, Node<T> element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new NullPointerException();
        }

        int counter = 0;

        Node<T> result = null;
        Node<T> copyList = list;

        for (int i = 0; i < size; i++) {
            if (counter != index - 2) {
                copyList = copyList.getNext();
                counter++;
            } else {
                result = copyList.getNext();
                copyList.setNext(element);
                element.setNext(result.getNext());
                break;
            }
        }

        return result;
    }

    public Node<T> deleteElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> result = null;

        if (index == 0) {
            result = list;
            list = list.getNext();
            size--;

        } else {
            Node<T> tempList = list;

            for (int i = 0; i < size; i++) {
                if (i != index - 1) {
                    tempList = tempList.getNext();
                } else {
                    result = tempList.getNext();

                    tempList.setNext(tempList.getNext().getNext());
                    size--;
                    break;
                }
            }
        }

        return result;
    }

    public Node<T> insertStart(Node<T> element) {
        if (element == null) {
            throw new NullPointerException();
        }

        if (list == null) {
            list = element;
            size++;
            return null;
        }

        element.setNext(list);
        list = element;
        size++;

        return list.getNext();
    }

    public void add(int index, Node<T> element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new NullPointerException();
        }

        if (index == 0) {
            insertStart(element);
            return;
        }

        Node<T> tempList = list;

        for (int i = 0; i < size; i++) {
            if (i != index - 1) {
                tempList = tempList.getNext();
            } else {
                element.setNext(tempList.getNext());
                tempList.setNext(element);
                size++;
                break;
            }
        }
    }

    public boolean deleteValue(Node<T> element) {
        if (element == null) {
            throw new NullPointerException();
        }

        if (list.getValue() != null && list.getValue().equals(element.getValue())) {
            list = list.getNext();
            size--;

            return true;
        }

        Node<T> tempList = list;

        while (tempList.getNext() != null) {
            if (!tempList.getNext().getValue().equals(element.getValue())) {
                tempList = tempList.getNext();
            } else {
                tempList.setNext(tempList.getNext().getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    public String toString() {
        StringBuilder lineInfo = new StringBuilder();
        lineInfo.append('{');

        Node<T> temp = list;

        for (int i = 0; i < size; i++) {
            lineInfo.append(temp.toString() + ",");
            temp = temp.getNext();
        }

        lineInfo.deleteCharAt(lineInfo.length() - 1);
        lineInfo.append("}");

        return lineInfo.toString();
    }

    public void revers() {
        if (list == null) {
            throw new NullPointerException();
        }

        Node<T> pre = null;
        Node<T> cur = list;
        Node<T> next = null;

        while (cur != null) {
            next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }

        list = pre;
    }

    public List<T> copyList() {
        if (size <= 0) {
            return null;
        }

        List<T> copyList = new List<>();
        Node<T> temp = list;

        for (int i = 0; i < size; i++) {
            copyList.add(i, new Node<>(temp.getValue()));
            temp = temp.getNext();
        }

        return copyList;
    }
}
