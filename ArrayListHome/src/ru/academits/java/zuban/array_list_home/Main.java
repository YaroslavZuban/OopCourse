package ru.academits.java.zuban.array_list_home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------ Задание 1 ------------------");

        readLinesFromFile();

        System.out.println("------------ Задание 2 ------------------");

        removeEvenNumbers();

        System.out.println("------------ Задание 3 ------------------");

        removeDuplicates();
    }

    public static void readLinesFromFile() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ben70\\IdeaProjects\\OopCourse\\ArrayListHome\\text.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            System.out.println(lines);
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    public static void removeEvenNumbers() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();

        System.out.println("Список до изменения: " + list);

        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println("Список после исключения четных чисел: " + list);
    }

    public static void removeDuplicates() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list.add((int) (Math.random() * 20));
        }

        List<Integer> uniqueNumbersList = new ArrayList<>();

        System.out.println("Список: " + list);

        for (Integer number : list) {
            if (!uniqueNumbersList.contains(number)) {
                uniqueNumbersList.add(number);
            }
        }

        System.out.println("Новый список с уникальными числами: " + uniqueNumbersList);
    }
}