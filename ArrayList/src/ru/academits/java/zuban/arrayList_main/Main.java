package ru.academits.java.zuban.arrayList_main;

import ru.academits.java.zuban.arrayList.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        testArrayList(10);
    }

    public static void testArrayList(int size) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();

        System.out.println("-----------------------------------isEmpty and size------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("Проверка arrayList1 на пустоту, должно быть false. Результат равен isEmpty: " + arrayList1.isEmpty());
        System.out.println("Проверка размера ArrayList, должно быть 0. Результат равен size:  " + arrayList1.size());

        for (int i = 0; i < size; i++) {
            arrayList1.add(i);
        }

        System.out.println("\n------------------------------isEmpty and size-----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("Проверка arrayList1 на пустоту, должно быть true. Результат равен isEmpty: " + arrayList1.isEmpty());
        System.out.println("Проверка размера ArrayList, должно быть " + size + ". Результат равен size:  " + arrayList1.size());

        System.out.println("\n------------------------------add-----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("Проверка добавлениея элементов массива c помощью функции add и получения массива функций toArray : " +
                Arrays.toString(arrayList1.toArray()));

        System.out.println("\n---------------------------------get--------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        for (int i = 0; i < size; i++) {
            System.out.println("Получим элемент ArrayList по индексу get( " + i + " ) = " + i + ", результат равен get( " + i + " ) = " + i);
        }

        System.out.println("\n----------------------------------contains-------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        for (int i = 0; i < size + 3; i++) {
            System.out.println("Проверка есть ли данный элемент в arrayList1. Элемент: " + i + " Результат:" + arrayList1.contains(i));
        }

        System.out.println("\n------------------------------------remove-----------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));


        System.out.println("Проверка удаления элемента remove. Индекс: " + 0 + " Результат:" + arrayList1.remove(0));
        System.out.println("Проверка удаления элемента remove. Индекс: " + 0 + " Результат:" + arrayList1.remove(0));
        System.out.println("Проверка удаления элемента remove. Индекс: " + (arrayList1.size() - 1) + " Результат:" + arrayList1.remove(arrayList1.size() - 1));
        System.out.println("Проверка удаления элемента remove. Индекс: " + (arrayList1.size() - 1) + " Результат:" + arrayList1.remove(arrayList1.size() - 1));

        System.out.println("\n-----------------------------------clear------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        arrayList1.clear();
        System.out.println("Удаление из ArrayList с помощью clear: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("\n-----------------------------------containsAll------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        java.util.ArrayList<Integer> arrayList2 = new java.util.ArrayList<>();

        System.out.println("Сравнение двух пустых ArrayList: " + arrayList1.containsAll(arrayList2));

        System.out.println("\n-------------------------------addAll----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        for (int i = 0; i < size; i++) {
            arrayList2.add(i);
        }

        arrayList1.addAll(arrayList2);

        System.out.println("Коллекция имеет следующие значения: " + Arrays.toString(arrayList2.toArray()));
        System.out.println("Добавление элементов из коллекции addAll: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("\n--------------------------------addAll---------------------------------------");

        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        arrayList1.addAll(8, arrayList2);

        System.out.println("Коллекция имеет следующие значения: " + Arrays.toString(arrayList2.toArray()));
        System.out.println("Добавление элементов из коллекции по индексу addAll: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("\n--------------------------------removeAll--------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList1.toArray()));

        java.util.ArrayList<Integer> arrayList3 = new java.util.ArrayList<>();

        for (int i = 0; i < size; i += 2) {
            arrayList3.add(i);
        }

        System.out.println("Коллекция имеет следующие значения: " + Arrays.toString(arrayList3.toArray()));

        arrayList1.removeAll(arrayList3);
        System.out.println("Проверим функцию removeAll: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("\n-------------------------------retainAll----------------------------------------");

        ArrayList<Integer> arrayList4 = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            arrayList4.add(i);
        }

        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList4.toArray()));
        System.out.println("Коллекция имеет следующие значения: " + Arrays.toString(arrayList3.toArray()));

        arrayList4.retainAll(arrayList3);
        System.out.println("Проверим функцию retainAll: " + Arrays.toString(arrayList4.toArray()));

        System.out.println("\n-------------------------------add по index----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList4.toArray()));

        arrayList4.add(0, 5);
        arrayList4.add(3, 6);

        System.out.println("Проверим функцию добавления по индексу: " + Arrays.toString(arrayList4.toArray()));

        System.out.println("\n-------------------------------toString(T[] a)----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + Arrays.toString(arrayList4.toArray()));

        Integer[] array1 = new Integer[4];
        System.out.println("Размер массива array1: " + array1.length + " Элементы массива равны: " + Arrays.toString(array1));

        array1 = arrayList4.toArray(array1);
        System.out.println("После операции toString(T[] a) размер массива array1: " + array1.length + " Элементы массива равны: " + Arrays.toString(array1));

        Integer[] array2 = new Integer[20];
        System.out.println("Размер массива array1: " + array2.length + " Элементы массива равны: " + Arrays.toString(array2));

        array2 = arrayList4.toArray(array2);
        System.out.println("После операции toString(T[] a) размер массива array1: " + array2.length + " Элементы массива равны: " + Arrays.toString(array2));
    }
}