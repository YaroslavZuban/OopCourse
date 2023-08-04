package ru.academits.java.zuban.arrayList;

import java.util.*;
import java.util.function.Consumer;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_LENGTH = 16;
    private int size;
    private Object[] elementData;

    public ArrayList(Object[] elementData) {
        if (elementData == null) {
            throw new NullPointerException("Переданный объект равен null");
        }

        size = elementData.length;
        this.elementData = Arrays.copyOf(elementData, elementData.length);
    }

    public ArrayList(int size) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Не возможно создать ArrayList размерностью: " + size);
        }

        elementData = new Object[size];
    }

    public ArrayList(ArrayList<E> arrayList) {
        if (arrayList == null) {
            throw new NullPointerException("Переданный объект равен null");
        }

        elementData = Arrays.copyOf(arrayList.elementData, arrayList.elementData.length);
    }

    public ArrayList() {
        elementData = new Object[INITIAL_LENGTH];
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

        for (Object elementDatum : elementData) {
            if (o.equals(elementDatum)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorArrayList();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        checkNotNull(e);

        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (elementData.length * 1.5));
        }

        elementData[size] = e;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        checkNotNull(o);

        for (int i = 0; i < elementData.length; i++) {
            if (o.equals(elementData[i])) {
                elementData[i] = null;
                shiftArray(i, elementData);

                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkNotCollectionNull(c, "containsAll");

        return Arrays.equals(elementData, c.toArray());
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkNotCollectionNull(c, "addAll");

        if (c.size() + size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) ((c.size() + size) * 1.5));
        }

        Object[] copyCollectionArray = c.toArray();

        if (copyCollectionArray.length == 0) {
            return false;
        }

        System.arraycopy(copyCollectionArray, 0, elementData, size, copyCollectionArray.length);
        size = size + copyCollectionArray.length;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {//дописать проверку
        checkNotCollectionNull(c, "addAll");
        validateIndex(index);

        growthArray(c.size());
        Object[] copyCollectionArray = c.toArray();

        if (copyCollectionArray.length == 0) {
            return false;
        }

        System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
        System.arraycopy(copyCollectionArray, 0, elementData, index, copyCollectionArray.length);

        size += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkNotCollectionNull(c, "removeAll");
        int originalSize = size;

        Set<Object> set = new HashSet<>(c);
        List<Object> resultList = new ArrayList<>();

        for (int i = 0; i < originalSize; i++) {
            Object element = elementData[i];

            if (!set.contains(element)) {
                resultList.add(element);
            }
        }

        if (originalSize == resultList.size()) {
            return false;
        }

        elementData = resultList.toArray();
        size = resultList.size();

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkNotCollectionNull(c, "retainAll");

        int originalSize = size;

        Set<Object> set = new HashSet<>(c);
        List<Object> resultList = new ArrayList<>();

        for (int i = 0; i < originalSize; i++) {
            Object element = elementData[i];

            if (set.contains(element)) {
                resultList.add(element);
            }
        }

        if (originalSize == resultList.size()) {
            return false;
        }

        elementData = resultList.toArray();
        size = resultList.size();

        return true;
    }

    @Override
    public void clear() {
        elementData = new Object[INITIAL_LENGTH];
        size = 0;
    }

    @Override
    public E get(int index) {
        validateIndex(index);

        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E oldElement = (E) elementData[index];
        elementData[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkNotNull(element);
        validateIndex(index);

        growthArray(size + 1);

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;

        size++;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        E result = (E) elementData[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }

        size--;

        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i > 0; i--) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }

        return -1;
    }

    private void shiftArray(int index, Object[] elementData) {
        for (int i = index; i < size - 1; i++) {
            if (i + 1 == size) {
                elementData[i] = null;
            } else {
                elementData[i] = elementData[i + 1];
            }
        }
    }

    private void checkNotCollectionNull(Collection<?> c, String nameFunction) {
        if (c == null) {
            throw new NullPointerException("Переденное значение в " + nameFunction + " равно null.");
        }
    }

    private void checkNotNull(Object value) {
        if (value == null) {
            throw new NullPointerException("Переданный объект равен null");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс " + index + " вне допустимого диапазона [0; " + size + "]");
        }
    }

    private void growthArray(int newSize) {
        int oldSize = elementData.length;

        while (size + newSize >= oldSize) {
            oldSize = (int) (oldSize * 1.5);
        }

        if (oldSize != elementData.length) {
            elementData = Arrays.copyOf(elementData, oldSize);
        }
    }

    private class IteratorArrayList implements Iterator<E>{
        int position=0;
        @Override
        public boolean hasNext() {
            return size != position;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new IndexOutOfBoundsException("Не существует элемента");
            }

            return (E)elementData[position++];
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