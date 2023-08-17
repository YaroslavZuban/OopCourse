package ru.academits.java.zuban.temperature.main;

import ru.academits.java.zuban.temperature.ui.GraphicalInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GraphicalInterface graphicalInterface = new GraphicalInterface();
            }
        });
    }
}