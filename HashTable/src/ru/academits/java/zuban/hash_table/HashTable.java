package ru.academits.java.zuban.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int INITIAL_LENGTH = 16;

    private ArrayList<E>[] elementsList;
    private int size;
    private int changesCount;

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new NoSuchElementException("Не возможно создать HashTable размерностью: " + arrayLength);
        }

        elementsList = new ArrayList[arrayLength];
    }

    public HashTable() {
        elementsList = new ArrayList[INITIAL_LENGTH];
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

        return elementsList[index] != null && elementsList[index].contains(o);
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
        int count = 0;

        for (List<E> list : elementsList) {
            if (list != null) {
                for (E element : list) {
                    array[count] = element;
                    count++;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        resizeArray();

        int index = getIndex(e);

        if (elementsList[index] == null) {
            elementsList[index] = new ArrayList<>();
        }

        elementsList[index].add(e);
        size++;
        changesCount++;

        return true;
    }

    private void resizeArray() {
        if (size == elementsList.length) {
            List<E>[] oldArrayLists = elementsList;

            //noinspection unchecked
            elementsList = new ArrayList[elementsList.length * 2];
            size = 0;

            for (List<E> list : oldArrayLists) {
                if (list != null) {
                    addAll(list);
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (elementsList[index] != null && elementsList[index].remove(o)) {
            size--;
            changesCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkArgumentNotNull(c, "containsAll");

        int matchCount = 0;

        for (Object element : c) {
            for (List<E> list : elementsList) {
                if (list != null) {
                    for (E elementList : list) {
                        if (Objects.equals(elementList, element)) {
                            matchCount++;
                        }
                    }
                }
            }
        }

        return matchCount == size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkArgumentNotNull(c, "addAll");

        for (E element : c) {
            if (!add(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkArgumentNotNull(c, "removeAll");

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
        checkArgumentNotNull(c, "retainAll");

        int oldSize = size;

        for (List<E> list : elementsList) {
            for (E element : list) {
                if (!c.contains(element)) {
                    remove(element);
                }
            }
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        if (size != 0) {
            size = 0;
            Arrays.fill(elementsList, null);
            changesCount++;
        }
    }

    private int getIndex(Object object) {
        return Math.abs((object == null ? 0 : object.hashCode()) % elementsList.length);
    }

    private static void checkArgumentNotNull(Object object, String nameFunction) {
        if (object == null) {
            throw new NullPointerException("Переданный объект в функцию " + nameFunction + " равен null");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator<>();
    }

    class HashTableIterator<T> implements Iterator<T> {
        private int passedElementsNumber;
        private int listArrayIndex;
        private int listIndex;
        private final int initialChangesCount = changesCount;

        @Override
        public boolean hasNext() {
            return passedElementsNumber != size;
        }

        @Override
        public T next() {
            if (initialChangesCount != changesCount) {
                throw new ConcurrentModificationException("Коллекция изменена.");
            }

            if (!hasNext()) {
                throw new IndexOutOfBoundsException("Не существует элемента");
            }

            while (elementsList[listArrayIndex] == null) {
                listArrayIndex++;
            }

            if (elementsList[listArrayIndex].size() >= listIndex) {
                listArrayIndex++;
                listIndex = 0;
            }

            if (listArrayIndex < elementsList.length) {
                passedElementsNumber++;
                //noinspection unchecked
                return (T) elementsList[listArrayIndex].get(listIndex++);
            }

            throw new NoSuchElementException("Не существует элемента");
        }
    }
}