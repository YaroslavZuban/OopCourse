package ru.academits.java.zuban.temperature.ui;

import ru.academits.java.zuban.temperature.translation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalInterface {
    private ScaleTranslator scaleTranslator;
    private String result;
    private String format = " C";
    private final JButton kelvinButton;
    private final JButton fahrenheitButton;
    private final JButton celsiusButton;
    private final JTextField valueInput;
    private final JLabel labelOutput;
    private int valueUniqueInput = 1;
    private int valueUniqueOutput = 1;

    public GraphicalInterface() {
        JFrame windowFrame = new JFrame("Перевод температуры");
        windowFrame.setVisible(true);
        windowFrame.setResizable(false);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.pack();

        windowFrame.setSize(500, 300);
        windowFrame.setLayout(new GridLayout(6, 1, 20, 10));

        JLabel textLabel = new JLabel("Выберите исходную температуру:");
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        windowFrame.add(textLabel);

        JPanel panelButton = new JPanel();
        GridLayout buttonGrid = new GridLayout(1, 3, 20, 20);

        panelButton.setLayout(buttonGrid);

        celsiusButton = new JButton("Цельсий");
        celsiusButton.setBackground(Color.GREEN);
        panelButton.add(celsiusButton);

        kelvinButton = new JButton("Кельвин");
        kelvinButton.setBackground(Color.GRAY);
        panelButton.add(kelvinButton);

        fahrenheitButton = new JButton("Фаренгейт");
        fahrenheitButton.setBackground(Color.GRAY);
        panelButton.add(fahrenheitButton);

        actionListenerInput();

        windowFrame.add(panelButton);

        JPanel panelGridInput = new JPanel();
        GridLayout gridInput = new GridLayout(1, 2, 30, 20);

        panelGridInput.setLayout(gridInput);
        JLabel textInput = new JLabel("Введите число:");
        textInput.setVerticalAlignment(JLabel.CENTER);
        textInput.setHorizontalAlignment(JLabel.RIGHT);
        panelGridInput.add(textInput);

        valueInput = new JTextField();
        valueInput.setText("0");
        valueInput.setHorizontalAlignment(JTextField.CENTER);
        panelGridInput.add(valueInput);

        windowFrame.add(panelGridInput);

        JLabel textOutputLabel = new JLabel("Выберите целевую температуру:");
        textOutputLabel.setVerticalAlignment(JLabel.CENTER);
        textOutputLabel.setHorizontalAlignment(JLabel.CENTER);
        windowFrame.add(textOutputLabel);

        JPanel panelResultButton = new JPanel();
        actionListenerOutput(panelResultButton);

        windowFrame.add(panelResultButton);

        labelOutput = new JLabel();
        labelOutput.setVerticalAlignment(JLabel.CENTER);
        labelOutput.setHorizontalAlignment(JLabel.CENTER);
        labelOutput.setText("Результат равен: ");

        windowFrame.add(labelOutput);
    }

    private void actionListenerInput() {
        celsiusButton.addActionListener(e -> {
            setButtonBackground(celsiusButton, kelvinButton, fahrenheitButton);

            valueUniqueInput = 1;
            translate();
            resultCount();
        });

        kelvinButton.addActionListener(e -> {
            setButtonBackground(kelvinButton, celsiusButton, fahrenheitButton);

            valueUniqueInput = 2;
            translate();
            resultCount();
        });

        fahrenheitButton.addActionListener(e -> {
            setButtonBackground(fahrenheitButton, celsiusButton, kelvinButton);

            valueUniqueInput = 3;
            translate();
            resultCount();
        });
    }

    private void actionListenerOutput(JPanel panelResultButton) {
        GridLayout resultButtonGrid = new GridLayout(1, 3, 20, 20);

        panelResultButton.setLayout(resultButtonGrid);

        JButton isCelsiusButton = new JButton("Цельсий");
        isCelsiusButton.setBackground(Color.GREEN);
        panelResultButton.add(isCelsiusButton);

        JButton isKelvinButton = new JButton("Кельвин");
        isKelvinButton.setBackground(Color.GRAY);
        panelResultButton.add(isKelvinButton);

        JButton isFahrenheitButton = new JButton("Фаренгейт");
        isFahrenheitButton.setBackground(Color.GRAY);
        panelResultButton.add(isFahrenheitButton);

        isCelsiusButton.addActionListener(e -> {
            setButtonBackground(isCelsiusButton, isKelvinButton, isFahrenheitButton);
            format = " C";

            valueUniqueOutput = 1;
            translate();
            resultCount();
        });

        isKelvinButton.addActionListener(e -> {
            setButtonBackground(isKelvinButton, isCelsiusButton, isFahrenheitButton);

            format = " K";

            valueUniqueOutput = 2;
            translate();
            resultCount();
        });

        isFahrenheitButton.addActionListener(e -> {
            setButtonBackground(isFahrenheitButton, isCelsiusButton, isKelvinButton);

            format = " F";

            valueUniqueOutput = 3;
            translate();
            resultCount();
        });
    }

    private void resultCount() {
        labelOutput.setText("Результат равен: " + result + format);
    }

    private static void setButtonBackground(JButton activeButton, JButton inactiveButton1, JButton inactiveButton2) {
        activeButton.setBackground(Color.GREEN);
        inactiveButton1.setBackground(Color.GRAY);
        inactiveButton2.setBackground(Color.GRAY);
    }

    private void translate() {
        try {
            if (valueUniqueOutput == 1) {
                translateNToCelsius();
            } else if (valueUniqueOutput == 2) {
                translateNToKelvin();
            } else {
                translateNToFahrenheit();
            }
        } catch (NumberFormatException e) {
            Error.getError();
        }
    }

    private void translateNToFahrenheit() {
        if (valueUniqueInput == 3) {
            result = valueInput.getText();
        } else {
            if (valueUniqueInput == 1) {
                scaleTranslator = new CelsiusToFahrenheitTranslator();
            } else if (valueUniqueInput == 2) {
                scaleTranslator = new KelvinToFahrenheitTranslator();
            }

            result = String.valueOf(scaleTranslator.translate(Double.parseDouble(valueInput.getText())));
        }

        formattingNumber();
    }

    private void translateNToCelsius() {
        if (valueUniqueInput == 1) {
            result = valueInput.getText();
        } else {
            if (valueUniqueInput == 2) {
                scaleTranslator = new KelvinToCelsiusTranslator();
            } else if (valueUniqueInput == 3) {
                scaleTranslator = new FahrenheitToCelsiusTranslator();
            }

            result = String.valueOf(scaleTranslator.translate(Double.parseDouble(valueInput.getText())));
        }

        formattingNumber();
    }

    private void translateNToKelvin() {
        if (valueUniqueInput == 2) {
            result = valueInput.getText();
        } else {
            if (valueUniqueInput == 1) {
                scaleTranslator = new CelsiusToKelvinTranslator();
            } else if (valueUniqueInput == 3) {
                scaleTranslator = new FahrenheitToKelvinTranslator();
            }

            result = String.valueOf(scaleTranslator.translate(Double.parseDouble(valueInput.getText())));
        }

        formattingNumber();
    }

    private void formattingNumber() {
        result = String.format("%.3f", Double.parseDouble(result));
    }
}