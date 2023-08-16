package ru.academits.java.zuban.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 16;

    private int changeCount;
    private int size;
    private E[] elements;

    public ArrayList(E[] elements) {
        if (elements == null) {
            throw new NullPointerException("Переданный объект равен null");
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
            elements = (E[]) new Object[1];
        } else {
            //noinspection unchecked
            elements = (E[]) new Object[capacity];
        }
    }

    public ArrayList(Collection<E> arrayList) {
        checkNotCollectionNull(arrayList, "ArrayList");

        //noinspection unchecked
        elements = (E[]) new Object[arrayList.size()];

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(arrayList.toArray(), 0, elements, 0, arrayList.size());
    }

    public ArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" [");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
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
        for (Object element : elements) {
            if (o.equals(element)) {
                return true;
            }
        }

        return false;
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
        int indexDeleteElement = indexOf(o);

        if (indexDeleteElement != -1) {
            remove(indexDeleteElement);
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

        addAll(size, c);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkNotCollectionNull(c, "addAll");
        checkIndex(index);

        ensureCapacity(c.size() + size);

        if (c.isEmpty()) {
            return false;
        }

        if (size - index > 0) {
            System.arraycopy(elements, index, elements, index + c.size(), size - index);
        }

        Object[] arrayCollection = c.toArray();
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(arrayCollection, 0, elements, index, arrayCollection.length);

        size += c.size();
        changeCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkNotCollectionNull(c, "removeAll");

        int originalSize = size;

        for (Object currentElement : c) {
            while (indexOf(currentElement) != -1) {
                remove(currentElement);
            }
        }

        return originalSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkNotCollectionNull(c, "retainAll");

        int originalSize = size;

        for (Object currentElement : elements) {
            if (c.contains(currentElement)) {
                remove(currentElement);
            }
        }

        return originalSize != size;
    }

    @Override
    public void clear() {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }

            changeCount++;
            size = 0;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        changeCount++;

        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        changeCount++;

        E oldElement = (E) elements[index];
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
        changeCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E deletedElement = (E) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        size--;
        changeCount++;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i >= 0; i--) {
            if (o.equals(elements[i])) {
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + size + "]");
        }
    }

    private void ensureCapacity(int minCapacity) {
        int capacitySize = elements.length;

        if (minCapacity > capacitySize) {
            capacitySize = (minCapacity * 2);
            elements = Arrays.copyOf(elements, capacitySize);
        }
    }

    private class ArrayListIterator implements Iterator<E> {
        private int index;
        private final int listModification = changeCount;

        @Override
        public boolean hasNext() {
            if (index > size) {
                throw new ArrayIndexOutOfBoundsException("Индекс вышел за придел списка.");
            }

            return size != index;
        }

        @Override
        public E next() {
            if (listModification != changeCount) {
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

    /**
     * реализация не нужна
     **/

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