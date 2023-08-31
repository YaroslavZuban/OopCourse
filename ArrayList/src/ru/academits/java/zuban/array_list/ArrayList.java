package ru.academits.java.zuban.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 16;

    private int changesCount;
    private int size;
    private E[] elements;

    public ArrayList(E[] elements) {
        checkCollectionIsNotNull(elements, "elements");

        size = elements.length;
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new NoSuchElementException("Не возможно создать ArrayList размерностью: " + size);
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    public ArrayList(Collection<E> collection) {
        checkCollectionIsNotNull(collection, "collection");

        //noinspection unchecked
        elements = (E[]) collection.toArray();
        size = collection.size();
    }

    public ArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, elements.length);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        add(size, e);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkCollectionIsNotNull(c, "collection");

        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkCollectionIsNotNull(c, "collection");

        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkCollectionIsNotNull(c, "collection by index");
        checkIndex(index);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(c.size() + size);

        if (size - index > 0) {
            System.arraycopy(elements, index, elements, index + c.size(), size - index);
        }

        int i = index;

        for (E element : c) {
            elements[i] = element;
            i++;
        }

        size += c.size();
        changesCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCollectionIsNotNull(c, "collection");

        if (isEmpty()) {
            return false;
        }

        int originalSize = size;

        for (Object element : c) {
            boolean isDeleteElement = remove(element);

            while (isDeleteElement) {
                isDeleteElement = remove(element);
            }
        }

        return originalSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollectionIsNotNull(c, "collection");

        if (isEmpty()) {
            return false;
        }

        int originalSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(elements[i]);
            }
        }

        return originalSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(elements, 0, size, null);

        changesCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        ensureCapacity(index + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;

        size++;
        changesCount++;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);

        E removedElement = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        size--;
        elements[size] = null;

        changesCount++;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    private static void checkCollectionIsNotNull(Object object, String argumentName) {
        if (object == null) {
            throw new NullPointerException("Переданные значение в " + argumentName + " равно null.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + size + "]");
        }
    }

    private void ensureCapacity(int minCapacity) {
        while (minCapacity > elements.length) {
            ensureCapacity();
        }
    }

    private void ensureCapacity() {
        int currentCapacity = elements.length;
        int newCapacity = Math.max(currentCapacity * 2, 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private class ArrayListIterator implements Iterator<E> {
        private int index;
        private final int initialChangesCount = changesCount;

        @Override
        public boolean hasNext() {
            return size != index;
        }

        @Override
        public E next() {
            if (initialChangesCount != changesCount) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (index >= size) {
                throw new NoSuchElementException("Не существует элемента");
            }

            if (index >= elements.length) {
                throw new ConcurrentModificationException("Не существует элемента");
            }

            E result = elements[index];
            index++;

            return result;
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}