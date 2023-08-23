package ru.academits.java.zuban.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int INITIAL_LENGTH = 16;

    private ArrayList<E>[] elementLists;
    private int size;
    private int changeCounts;

    public HashTable(int arraySize) {
        if (arraySize <= 0) {
            throw new NoSuchElementException("Не возможно создать HashTable размерностью: " + arraySize);
        }

        elementLists = new ArrayList[arraySize];
    }

    public HashTable() {
        elementLists = new ArrayList[INITIAL_LENGTH];
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
        int index = getObjectByIndex(o);

        if (elementLists[index] == null) {
            return false;
        }

        //noinspection rawtypes
        List elementList = (List) elementLists[index];

        return elementList.contains(o);
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
        checkArgumentNotNull(a, "toArray");

        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) new Object[size];
        }

        //noinspection unchecked
        fillArray((E[]) a);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void fillArray(E[] array) {
        int currentArrayIndex = 0;

        //noinspection rawtypes
        for (List list : elementLists) {
            if (list != null) {
                for (Object element : list) {
                    //noinspection unchecked
                    array[currentArrayIndex] = (E) element;
                    currentArrayIndex++;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        resizeArray();

        int index = getObjectByIndex(e);

        if (elementLists[index] == null) {
            elementLists[index] = new ArrayList<>();
        }

        //noinspection rawtypes
        List collision = (List) elementLists[index];
        //noinspection unchecked
        collision.add(e);
        size++;
        changeCounts++;

        return true;
    }

    private void resizeArray() {
        if (size == elementLists.length) {
            //noinspection rawtypes
            List[] arrayValue = elementLists;

            //noinspection unchecked
            elementLists = new ArrayList[elementLists.length * 2];
            size = 0;

            //noinspection rawtypes
            for (List list : arrayValue) {
                if (list != null) {
                    for (Object element : list) {
                        //noinspection SingleStatementInBlock,unchecked
                        add((E) element);
                    }
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = getObjectByIndex(o);

        if (elementLists[index] == null) {
            return false;
        }

        //noinspection rawtypes
        List collision = (List) elementLists[index];
        boolean isRemoveValue = collision.remove(o);

        if (isRemoveValue) {
            size--;
            changeCounts++;
        }

        return isRemoveValue;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkArgumentNotNull(c, "containsAll");

        for (List list : elementLists) {
            if (list != null) {
                for (Object element : list) {
                    if (!c.contains(element)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkArgumentNotNull(c, "addAll");

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkArgumentNotNull(c, "removeAll");

        int oldSize = size;

        for (Object element : c) {
            boolean changed = remove(element);

            while (changed) {
                changed = remove(element);
            }
        }

        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkArgumentNotNull(c, "retainAll");

        int oldSize = size;

        for (Object element : toArray(new Object[0])) {
            if (!c.contains(element)) {
                remove(element);
            }
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(elementLists, new ArrayList<>());
        changeCounts++;
    }

    private int getObjectByIndex(Object object) {
        return Math.abs((31 + (object == null ? 0 : object.hashCode())) % elementLists.length);
    }

    private static void checkArgumentNotNull(Object object, String name) {
        if (object == null) {
            throw new NullPointerException("Переданный объект " + name + " равен null");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator<>();
    }

    class HashTableIterator<T> implements Iterator<T> {
        private int elementCount;
        private int rowIndex;
        private int columnIndex;
        private final int modificationCount = changeCounts;

        @Override
        public boolean hasNext() {
            return elementCount != size;
        }

        @Override
        public T next() {
            if (modificationCount != changeCounts) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (!hasNext()) {
                throw new IndexOutOfBoundsException("Не существует элемента");
            }

            while (elementLists[rowIndex] == null) {
                rowIndex++;
            }

            if (elementLists[rowIndex].size() >= columnIndex) {
                rowIndex++;
                columnIndex = 0;
            }

            //noinspection unchecked
            T result = (T) elementLists[rowIndex].get(columnIndex);
            columnIndex++;

            elementCount++;

            return result;
        }
    }
}