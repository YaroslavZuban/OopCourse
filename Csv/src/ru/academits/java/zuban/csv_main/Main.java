package ru.academits.java.zuban.csv_main;

import ru.academits.java.zuban.csv.Csv;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                Csv.convertFileCsvToHtml(args[0], args[1]);
            } else {
                System.out.println("Пожалуйста, укажите два параметра:");
                System.out.println("Первый параметр - исходный файл (например, name_file.txt)");
                System.out.println("Второй параметр - результирующий файл (например, name_file.csv)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка: Не удалось выполнить операцию с файлом.");
        }
    }
}