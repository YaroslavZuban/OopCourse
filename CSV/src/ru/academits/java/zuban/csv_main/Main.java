package ru.academits.java.zuban.csv_main;

import ru.academits.java.zuban.csv.Csv;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvPath = "C:/Users/ben70/IdeaProjects/OopCourse/CSV/text.csv";
        String htmlPath = "html.html";

        try {
            new Csv().convertFileCsvToHtml(args[0], args[1]);
        }catch (IOException e) {
            System.out.println("Файл не получилось открыть.");
        }

    }
}