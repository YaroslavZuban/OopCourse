package ru.academits.java.zuban.array_list_home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------ Задание 1 ------------------");

        List<String> lines = readLinesFromFile();
        System.out.println(lines);

        System.out.println();
        System.out.println("------------ Задание 2 ------------------");

        List<Integer> list1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }

        System.out.println("Список до изменения: " + list1);

        removeEvenNumbers(list1);

        System.out.println("Список после исключения четных чисел: " + list1);

        System.out.println();
        System.out.println("------------ Задание 3 ------------------");

        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list2.add((int) (Math.random() * 10));
        }

        System.out.println("Список: " + list2);

        List<Integer> list3 = removeDuplicates(list2);

        System.out.println("Новый список с уникальными числами: " + list3);
    }

    public static List<String> readLinesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ben70\\IdeaProjects\\OopCourse\\ArrayListHome\\text.txt"))) {
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }

        return null;
    }

    public static void removeEvenNumbers(List<Integer> list) {
        //noinspection Java8CollectionRemoveIf
        for (Integer element : list) {
            if (element % 2 == 0) {
                list.remove(element);
            }
        }
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> listUnique = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!listUnique.contains(number)) {
                listUnique.add(number);
            }
        }

        return listUnique;
    }
}