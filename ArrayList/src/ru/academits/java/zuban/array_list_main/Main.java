package ru.academits.java.zuban.array_list_main;

import ru.academits.java.zuban.array_list.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();

        System.out.println("-----------------------------------isEmpty and size------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        System.out.println("Проверка arrayList1 на пустоту, должно быть false. Результат равен isEmpty: " + arrayList1.isEmpty());
        System.out.println("Проверка размера ArrayList, должно быть 0. Результат равен size:  " + arrayList1.size());

        for (int i = 0; i < 10; i++) {
            arrayList1.add(i);
        }

        System.out.println();
        System.out.println("------------------------------isEmpty and size-----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        System.out.println("Проверка arrayList1 на пустоту, должно быть true. Результат равен isEmpty: " + arrayList1.isEmpty());
        System.out.println("Проверка размера ArrayList, должно быть " + 10 + ". Результат равен size:  " + arrayList1.size());

        System.out.println();
        System.out.println("------------------------------add-----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        System.out.println("Проверка добавление элементов массива c помощью функции add и получения массива функций toArray: " +
                arrayList1);

        System.out.println();
        System.out.println("---------------------------------get--------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        for (int i = 0; i < 10; i++) {
            System.out.println("Получим элемент ArrayList по индексу get( " + i + " ) = " + i + ", результат равен get( " + i + " ) = " + i);
        }

        System.out.println();
        System.out.println("----------------------------------contains-------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        for (int i = 0; i < 10 + 3; i++) {
            System.out.println("Проверка есть ли данный элемент в arrayList1. Элемент: " + i + " Результат: " + arrayList1.contains(i));
        }

        System.out.println();
        System.out.println("------------------------------------remove-----------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        System.out.println("Проверка удаления элемента remove. Индекс: 0 Результат: " + arrayList1.remove(0));
        System.out.println("Проверка удаления элемента remove. Индекс: 0 Результат: " + arrayList1.remove(0));
        System.out.println("Проверка удаления элемента remove. Индекс: " + (arrayList1.size() - 1) + " Результат: " + arrayList1.remove(arrayList1.size() - 1));
        System.out.println("Проверка удаления элемента remove. Индекс: " + (arrayList1.size() - 1) + " Результат: " + arrayList1.remove(arrayList1.size() - 1));

        System.out.println();
        System.out.println("-----------------------------------clear------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        arrayList1.clear();
        System.out.println("Удаление из ArrayList с помощью clear: " + arrayList1);

        System.out.println();
        System.out.println("-----------------------------------containsAll------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        java.util.ArrayList<Integer> arrayList2 = new java.util.ArrayList<>();

        System.out.println("Сравнение двух пустых ArrayList: " + arrayList1.containsAll(arrayList2));

        System.out.println();
        System.out.println("-------------------------------addAll----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        for (int i = 0; i < 10; i++) {
            arrayList2.add(i);
        }

        arrayList1.addAll(arrayList2);

        System.out.println("Коллекция имеет следующие значения: " + arrayList2);
        System.out.println("Добавление элементов из коллекции addAll: " + arrayList1);

        System.out.println();
        System.out.println("--------------------------------addAll---------------------------------------");

        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        arrayList1.addAll(8, arrayList2);

        System.out.println("Коллекция имеет следующие значения: " + arrayList2);
        System.out.println("Добавление элементов из коллекции по индексу addAll: " + arrayList1);

        System.out.println();
        System.out.println("--------------------------------removeAll--------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList1);

        java.util.ArrayList<Integer> arrayList3 = new java.util.ArrayList<>();

        for (int i = 0; i < 10; i += 2) {
            arrayList3.add(i);
        }

        System.out.println("Коллекция имеет следующие значения: " + arrayList3);

        arrayList1.removeAll(arrayList3);
        System.out.println("Проверим функцию removeAll: " + arrayList1);

        System.out.println();
        System.out.println("-------------------------------retainAll----------------------------------------");
        ArrayList<Integer> arrayList4 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arrayList4.add(i);
        }

        System.out.println("ArrayList содержит следующие элементы: " + arrayList4);
        System.out.println("Коллекция имеет следующие значения: " + arrayList3);

        arrayList4.retainAll(arrayList3);
        System.out.println("Проверим функцию retainAll: " + arrayList4);

        System.out.println();
        System.out.println("-------------------------------add по index----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList4);

        arrayList4.add(0, 5);
        arrayList4.add(3, 6);

        System.out.println("Проверим функцию добавления по индексу: " + arrayList4);

        System.out.println();
        System.out.println("-------------------------------toString(T[] a)----------------------------------------");
        System.out.println("ArrayList содержит следующие элементы: " + arrayList4);

        Integer[] array1 = new Integer[4];
        System.out.println("Размер массива array1: " + array1.length + " Элементы массива равны: " + Arrays.toString(array1));

        array1 = arrayList4.toArray(array1);
        System.out.println("После операции toArray(T[] a) размер массива array1: " + array1.length + " Элементы массива равны: " + Arrays.toString(array1));

        Integer[] array2 = new Integer[20];
        System.out.println("Размер массива array1: " + array2.length + " Элементы массива равны: " + Arrays.toString(array2));

        array2 = arrayList4.toArray(array2);
        System.out.println("После операции toArray(T[] a) размер массива array1: " + array2.length + " Элементы массива равны: " + Arrays.toString(array2));
    }
}