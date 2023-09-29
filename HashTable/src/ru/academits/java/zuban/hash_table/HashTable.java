package ru.academits.java.zuban.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int INITIAL_LENGTH = 16;

    private ArrayList<E>[] lists;
    private int size;
    private int changesCount;

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new NoSuchElementException("Невозможно создать HashTable размерностью: " + arrayLength);
        }

        //noinspection unchecked
        lists = new ArrayList[arrayLength];
    }

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[INITIAL_LENGTH];
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
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        //noinspection unchecked
        fillArray((E[]) array);

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        checkArgumentNotNull(a, "a");

        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        //noinspection unchecked
        fillArray((E[]) a);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void fillArray(E[] array) {
        int i = 0;

        for (List<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    array[i] = element;
                    i++;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        resizeArray();

        int index = getIndex(e);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(e);
        size++;
        changesCount++;

        return true;
    }

    private void resizeArray() {
        if (size == lists.length) {
            List<E>[] oldListsArray = lists;

            //noinspection unchecked
            lists = new ArrayList[lists.length * 2];
            size = 0;

            for (List<E> list : oldListsArray) {
                if (list != null) {
                    addAll(list);
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
            size--;
            changesCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkArgumentNotNull(c, "c");

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkArgumentNotNull(c, "c");

        boolean isAdded = false;

        for (E element : c) {
            if (add(element)) {
                isAdded = true;
            }
        }

        return isAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkArgumentNotNull(c, "c");

        int oldSize = size;

        for (Object element : c) {
            boolean isModified = remove(element);

            while (isModified) {
                isModified = remove(element);
            }
        }

        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkArgumentNotNull(c, "c");

        int oldSize = size;
        size = 0;

        for (List<E> list : lists) {
            if (list != null) {
                list.retainAll(c);
                size += list.size();
            }
        }

        if (oldSize != size) {
            changesCount++;
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        if (size != 0) {
            size = 0;
            Arrays.fill(lists, null);
            changesCount++;
        }
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
    }

    private static void checkArgumentNotNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new NullPointerException("Аргумент '" + argumentName + "' не может быть null");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentArrayIndex;
        private int currentListIndex;
        private final int expectedModCount;
        private int countPassedElement;

        public HashTableIterator() {
            currentArrayIndex = 0;
            currentListIndex = 0;
            expectedModCount = changesCount;
        }

        @Override
        public boolean hasNext() {
            return countPassedElement < size;
        }

        @Override
        public E next() {
            if (expectedModCount != changesCount) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            E result = lists[currentArrayIndex].get(currentListIndex);

            currentListIndex++;

            if (currentListIndex == lists[currentArrayIndex].size()) {
                currentArrayIndex++;
                currentListIndex = 0;
            }

            countPassedElement++;

            return result;
        }
    }
}