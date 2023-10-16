package ru.academits.java.zuban.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 16;

    private int changesCount;
    private int size;
    private E[] elements;

    public ArrayList(E[] elements) {
        checkIsNotNull(elements, "elements");

        size = elements.length;
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Невозможно создать ArrayList с вместимостью: " + capacity +
                    ". Значение должно быть больше или равное: 0");
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    public ArrayList(Collection<E> collection) {
        checkIsNotNull(collection, "collection");

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
        System.arraycopy(elements, 0, a, 0, size);

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
        checkIsNotNull(c, "collection");

        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIsNotNull(c, "collection");

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона от 0 до " + size);
        }

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
        checkIsNotNull(c, "collection");

        if (isEmpty()) {
            return false;
        }

        int originalSize = size;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                remove(i);
            }
        }

        return originalSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkIsNotNull(c, "collection");

        if (isEmpty()) {
            return false;
        }

        int originalSize = size;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
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
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона от 0 до" + size);
        }

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;

        size++;
        changesCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

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

    private static void checkIsNotNull(Object object, String argumentName) {
        if (object == null) {
            throw new NullPointerException("Переданное значение в " + argumentName + " равно null.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона от 0 до " + (size - 1));
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    private void increaseCapacity() {
        if (elements.length == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[1];
        } else {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    private class ArrayListIterator implements Iterator<E> {
        private int index;
        private final int initialChangesCount = changesCount;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (initialChangesCount != changesCount) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
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