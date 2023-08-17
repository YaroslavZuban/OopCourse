package ru.academits.java.zuban.temperature.ui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Error {
    public static void errorConsole() {
        //noinspection DuplicatedCode
        JDialog dialog = createDialog();
        dialog.setLocationRelativeTo(null);

        JLabel labelText = new JLabel("Не верное значение");
        JButton button = new JButton("ок");

        dialog.add(labelText, BorderLayout.NORTH);

        button.addActionListener(e -> {
            dialog.setVisible(false);
        });

        button.setSize(20, 10);
        dialog.add(button, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static JDialog createDialog() {
        JDialog dialog = new JDialog(new JDialog(), "Ошибка", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(180, 90);
        return dialog;
    }
}
