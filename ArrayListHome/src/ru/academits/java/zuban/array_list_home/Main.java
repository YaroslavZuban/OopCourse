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

        List<String> lines = readLinesFromFile("C:/Users/ben70/IdeaProjects/OopCourse/ArrayListHome/text.txt");
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

        List<Integer> listWithoutEvenNumbers = getWithoutDuplicatesList(numbersList2);
        System.out.println("Новый список с уникальными числами: " + listWithoutEvenNumbers);
    }

    public static List<String> readLinesFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла");
        }

        return null;
    }

    public static void removeEvenNumbers(List<Integer> numbersList) {
        for (int i = 0; i < numbersList.size(); i++) {
            Integer element = numbersList.get(i);

            if (element % 2 == 0) {
                numbersList.remove(element);
            }
        }
    }

    public static List<Integer> getWithoutDuplicatesList(List<Integer> numbersList) {
        List<Integer> listWithoutEvenNumbers = new ArrayList<>(numbersList.size());

        for (Integer number : numbersList) {
            if (!listWithoutEvenNumbers.contains(number)) {
                listWithoutEvenNumbers.add(number);
            }
        }

        return listWithoutEvenNumbers;
    }
}