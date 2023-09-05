package ru.academits.java.zuban.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------ Задание 1 ------------------");

        List<String> lines = null;

        try {
            lines = readLinesFromFile("C:/Users/ben70/IdeaProjects/OopCourse/ArrayListHome/text.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла");

        }

        System.out.println("Ошибка при открытии файла");

        System.out.println(lines);

        System.out.println();
        System.out.println("------------ Задание 2 ------------------");

        List<Integer> numbersList1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbersList1.add(i);
        }

        System.out.println("Список до изменения: " + numbersList1);

        removeEvenNumbers(numbersList1);
        System.out.println("Список после исключения четных чисел: " + numbersList1);

        System.out.println();
        System.out.println("------------ Задание 3 ------------------");

        List<Integer> numbersList2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbersList2.add((int) (Math.random() * 10));
        }

        System.out.println("Список: " + numbersList2);

        List<Integer> listWithoutDuplicates = getListWithoutDuplicates(numbersList2);
        System.out.println("Новый список с уникальными числами: " + listWithoutDuplicates);
    }

    public static List<String> readLinesFromFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void removeEvenNumbers(List<Integer> numbersList) {
        for (int i = numbersList.size() - 1; i >= 0; i--) {
            Integer element = numbersList.get(i);

            if (element % 2 == 0) {
                numbersList.remove(element);
            }
        }
    }

    public static List<Integer> getListWithoutDuplicates(List<Integer> numbersList) {
        List<Integer> listWithoutDuplicates = new ArrayList<>(numbersList.size());

        for (Integer number : numbersList) {
            if (!listWithoutDuplicates.contains(number)) {
                listWithoutDuplicates.add(number);
            }
        }

        return listWithoutDuplicates;
    }
}