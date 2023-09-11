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

        int matchCount = 0;

        for (Object element : c) {
            for (List<E> list : lists) {
                if (list != null) {
                    //noinspection SuspiciousMethodCalls
                    if (list.contains(element)) {
                        matchCount++;
                    }
                }
            }
        }

        return matchCount == size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkArgumentNotNull(c, "c");

        boolean isAddAll = false;

        for (E element : c) {
            if (add(element)) {
                isAddAll = true;
            }
        }

        return isAddAll;
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
        int newSize = 0;

        for (List<E> list : lists) {
            if (list != null) {
                list.retainAll(c);
                newSize += list.size();
            }
        }

        size = newSize;

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
        return Math.abs((object == null ? 0 : object.hashCode()) % lists.length);
    }

    private static void checkArgumentNotNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new NullPointerException("Аргумент '" + argumentName + "' не может быть null");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator<>();
    }

    class HashTableIterator<T> implements Iterator<T> {
        private int passedElementsCount;
        private int arrayIndex;
        private int listIndex;
        private final int initialChangesCount = changesCount;

        @Override
        public boolean hasNext() {
            return passedElementsCount != size;
        }

        @Override
        public T next() {
            if (initialChangesCount != changesCount) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            while (lists[arrayIndex] == null) {
                arrayIndex++;
            }

            if (lists[arrayIndex].size() <= listIndex) {
                arrayIndex++;
                listIndex = 0;
            }

            if (arrayIndex < lists.length) {
                passedElementsCount++;
                //noinspection unchecked
                return (T) lists[arrayIndex].get(listIndex++);
            }

            throw new NoSuchElementException("Коллекция закончилась.");
        }
    }
}