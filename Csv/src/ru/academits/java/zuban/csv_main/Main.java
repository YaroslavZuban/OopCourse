package ru.academits.java.zuban.csv_main;

import ru.academits.java.zuban.csv.Csv;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Csv().convertFileCsvToHtml(args[0], args[1]);
        } catch (IOException e) {
            System.out.println("Файл не получилось открыть.");
        }
    }
}