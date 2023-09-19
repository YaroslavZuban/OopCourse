package ru.academits.java.zuban.csv_main;

import ru.academits.java.zuban.csv.Csv;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvPath = "C:/Users/ben70/IdeaProjects/OopCourse/CSV/text.csv";
        String htmlPath = "html.html";

        try {
            new Csv().convertFileCsvToHtml(csvPath,htmlPath);
        }catch (IOException e) {
            System.out.println("Файл не получилось открыть.");
        }
    }
}