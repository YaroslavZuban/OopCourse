package ru.academits.java.zuban.temperature_main;

import ru.academits.java.zuban.temperature_UI.GraphicalInterface;

public class Main {
    public static void main(String[] args) {
        GraphicalInterface graphicalInterface = new GraphicalInterface();
        graphicalInterface.setVisible(true);
        graphicalInterface.pack();
    }
}