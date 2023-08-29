package ru.academits.java.zuban.csv_main;

import ru.academits.java.zuban.csv.CSV;

public class Main {
    public static void main(String[] args) {
        String csvPath = "C:/Users/ben70/IdeaProjects/OopCourse/CSV/text.csv";
        String htmlPath = "html.html";

        CSV cvs = new CSV();
        cvs.conversionFileCvsToHtml(csvPath, htmlPath);
    }
}