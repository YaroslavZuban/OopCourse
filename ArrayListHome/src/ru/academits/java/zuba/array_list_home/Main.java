package ru.academits.java.zuba.array_list_home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------ Задание 1 ------------------");

        List<String> list1 = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ben70\\IdeaProjects\\OopCourse\\ArrayListHome\\text.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                list1.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list1);

        System.out.println("------------ Задание 2 ------------------");

        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list2.add(i);
        }

        System.out.println("Лист до изменения: " + list2);

        for (int i = 0; i < list2.size(); i++) {
            Integer value = list2.get(i);

            if (value % 2 == 0) {
                list2.remove(value);
            }
        }

        System.out.println("Лист после удаления четных числе: " + list2);

        System.out.println("------------ Задание 3 ------------------");

        List<Integer> list3 = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list3.add((int) (Math.random() * 20));
        }

        System.out.println("Лист: " + list3);

        List<Integer> list4 = list3.stream().distinct().toList();
        System.out.println("Новый лист без повторений числе: " + list4);
    }
}