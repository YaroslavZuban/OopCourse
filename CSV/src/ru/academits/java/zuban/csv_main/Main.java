package ru.academits.java.zuban.csv_main;

import ru.academits.java.zuban.csv.CSV;

public class Main {
    public static void main(String[] args) {
        CSV cvs = new CSV();

        cvs.conversionFileCVSToHTML("C:\\Users\\ben70\\IdeaProjects\\OopCourse\\CSV\\text.csv", "html.html");
    }
}