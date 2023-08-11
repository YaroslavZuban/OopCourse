package ru.academits.java.zuban.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int INITIAL_LENGTH = 16;
    private Object[] values;
    private int size;

    public HashTable(int sizeArray) {
        values = new Object[sizeArray];
    }

    public HashTable() {
        values = new Object[INITIAL_LENGTH];
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
        checkNotNull(o);

        int index = hashCode(o);

        if (checkCellIndex(index)) {
            return false;
        }

        //noinspection rawtypes
        List collision = (List) values[index];

        for (Object object : collision) {
            if (object.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        //noinspection unchecked
        fillingArray((E[]) array);

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        checkNotNull(a);

        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) new Object[size];
        }

        //noinspection unchecked
        fillingArray((E[]) a);

        return a;
    }

    private void fillingArray(E[] array) {
        int indexCurrentArray = 0;

        for (Object value : values) {
            if (value != null) {
                //noinspection rawtypes
                List collision = (List) value;

                for (Object object : collision) {
                    //noinspection unchecked
                    array[indexCurrentArray++] = (E) object;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        checkNotNull(e);

        increasingArraySize();

        int index = hashCode(e);

        if (checkCellIndex(index)) {
            values[index] = new ArrayList<>();
        }

        //noinspection rawtypes
        List collision = (List) values[index];
        //noinspection unchecked
        collision.add(e);
        size++;

        return true;
    }

    private void increasingArraySize() {
        if (counterCellNull(values) == 0) {
            Object[] arrayValue = values;

            values = new Object[values.length * 2];
            size = 0;

            for (Object o : arrayValue) {
                if (o != null) {
                    //noinspection rawtypes
                    List collision = (List) o;

                    for (Object object : collision) {
                        //noinspection SingleStatementInBlock,unchecked
                        add((E) object);
                    }
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        checkNotNull(o);

        int index = hashCode(o);

        if (checkCellIndex(index)) {
            return false;
        }

        //noinspection rawtypes
        List collision = (List) values[index];
        boolean isRemoveValue = collision.remove(o);

        if (isRemoveValue) {
            size--;
        }

        return isRemoveValue;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkNotCollectionNull(c, "containsAll");

        return Arrays.equals(toArray(), c.toArray());
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkNotCollectionNull(c, "addAll");

        Object[] arrayCollection = c.toArray();

        for (Object object : arrayCollection) {
            //noinspection unchecked
            add((E) object);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkNotCollectionNull(c, "removeAll");

        int oldSize = size;
        Object[] arrayCollection = c.toArray();

        for (Object object : arrayCollection) {
            while (contains(object)) {
                remove(object);
            }
        }

        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkNotCollectionNull(c, "retainAll");

        int oldSize = size;
        Object[] arrayTable = toArray();

        for (Object object : arrayTable) {
            while (!c.contains(object) && contains(object)) {
                remove(object);
            }
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        size = 0;
        values = new Object[INITIAL_LENGTH];
    }

    private int hashCode(Object object) {
        return Math.abs(object.hashCode() % values.length);
    }

    private static void checkNotNull(Object value) {
        if (value == null) {
            throw new NullPointerException("Переданный объект равен null");
        }
    }

    private static int counterCellNull(Object[] array) {
        int count = 0;

        for (Object o : array) {
            if (o == null) {
                count++;
            }
        }

        return count;
    }

    private static void checkNotCollectionNull(Collection<?> c, String nameFunction) {
        if (c == null) {
            throw new NullPointerException("Переденное значение в " + nameFunction + " равно null.");
        }
    }

    private boolean checkCellIndex(int index) {
        return values[index] == null;
    }

    @SuppressWarnings("ClassEscapesDefinedScope")
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>();
    }

    class Iterator<T> implements java.util.Iterator<T> {
        Object[] array = toArray();
        int position = 0;

        @Override
        public boolean hasNext() {
            return position != array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("Не существует элемента");
            }

            //noinspection unchecked
            return (T) array[position++];
        }
    }
}