package ru.academits.java.zuban.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 16;

    private int changeCounts;
    private int size;
    private E[] elements;

    public ArrayList(E[] elements) {
        if (elements == null) {
            throw new NullPointerException("Переданный массив равен null");
        }

        size = elements.length;
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new NoSuchElementException("Не возможно создать ArrayList размерностью: " + size);
        }

        if (capacity == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[]{};
        } else {
            //noinspection unchecked
            elements = (E[]) new Object[capacity];
        }
    }

    public ArrayList(Collection<E> collection) {
        checkNotCollectionNull(collection, "ArrayList");

        //noinspection unchecked
        elements = (E[]) collection.toArray(new Object[0]);
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
        checkNotCollectionNull(c, "containsAll");

        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkNotCollectionNull(c, "addAll");

        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkNotCollectionNull(c, "addAll");

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + size + "]");
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
        changeCounts++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkNotCollectionNull(c, "removeAll");

        if (isEmpty()) {
            return false;
        }

        int originalSize = size;

        for (Object element : c) {
            boolean changed = remove(element);

            while (changed) {
                changed = remove(element);
            }
        }

        return originalSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkNotCollectionNull(c, "retainAll");

        if (isEmpty()) {
            return false;
        }

        int originalSize = size;

        for (Object element : toArray(new Object[0])) {
            if (!c.contains(element)) {
                remove(element);
            }
        }

        return originalSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(elements, null);

        changeCounts++;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = (E) elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + size + "]");
        }

        ensureCapacity(index + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;

        size++;
        changeCounts++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = (E) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        size--;
        elements[size] = null;

        changeCounts++;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    private static void checkNotCollectionNull(Collection<?> c, String functionName) {
        if (c == null) {
            throw new NullPointerException("Переденные значение в " + functionName + " равно null.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
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
        private final int modificationCount = changeCounts;

        @Override
        public boolean hasNext() {
            return size != index;
        }

        @Override
        public E next() {
            if (modificationCount != changeCounts) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (!hasNext()) {
                throw new IndexOutOfBoundsException("Не существует элемента");
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