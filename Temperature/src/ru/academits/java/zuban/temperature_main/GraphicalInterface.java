package ru.academits.java.zuban.temperature_main;

import ru.academits.java.zuban.temperature.Temperature;
import ru.academits.java.zuban.temperature.TemperatureCelsius;
import ru.academits.java.zuban.temperature.TemperatureFahrenheit;
import ru.academits.java.zuban.temperature.TemperatureKelvin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GraphicalInterface extends JFrame {
    private Temperature temperatureInterface;
    private Double resultTemperature;
    private Double value;
    private final JButton kelvin;
    private final JButton celsius;
    private final JButton fahrenheit;
    private final JLabel resultLabel;
    private final JTextField countLabel;

    public GraphicalInterface() {
        super("Перевод температуры");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labelTranslation = new JLabel("Перевод в");
        countLabel = new JTextField("0", 15);

        kelvin = new JButton("Кельвин");
        celsius = new JButton("Цельсий");
        fahrenheit = new JButton("Фаренгейт");

        resultLabel = new JLabel("Результат: " + 0 + " C");

        JButton isFahrenheit = new JButton("в Фаренгейт");
        JButton isKelvin = new JButton("в Кельвин");
        JButton isCelsius = new JButton("в Цельсий");

        JPanel panel = new JPanel(new FlowLayout());
        JPanel panelIsTemperature = new JPanel(new FlowLayout());

        temperatureInterface = new TemperatureCelsius();

        String imagePath = "/ru/academits/java/zuban/temperature_image/thermometer.png";
        URL imageURL = getClass().getResource(imagePath);

        if (imageURL != null) {
            setIconImage(new ImageIcon(imageURL).getImage());
        }

        try {
            value = Double.parseDouble(countLabel.getText());
        } catch (Exception exception) {
            errorConsole();
        }

        isCelsius.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temperatureInterface = new TemperatureCelsius();
            }
        });

        isKelvin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temperatureInterface = new TemperatureKelvin();
            }
        });

        isFahrenheit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temperatureInterface = new TemperatureFahrenheit();
            }
        });

        set();

        panelIsTemperature.add(isCelsius);
        panelIsTemperature.add(isKelvin);
        panelIsTemperature.add(isFahrenheit);

        add(panelIsTemperature, BorderLayout.PAGE_START);

        panel.add(countLabel, BorderLayout.CENTER);
        panel.add(labelTranslation);

        panel.add(celsius);
        panel.add(kelvin);
        panel.add(fahrenheit);

        add(resultLabel, BorderLayout.PAGE_END);
        add(panel, BorderLayout.CENTER);
    }

    private JDialog createDialog() {
        JDialog dialog = new JDialog(this, "Ошибка", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(180, 90);
        return dialog;
    }

    private void set() {
        celsius.addActionListener(t -> {
            try {
                value = Double.parseDouble(countLabel.getText());
                resultTemperature = temperatureInterface.celsius(value);
                resultCount();
            } catch (Exception exception) {
                errorConsole();
            }
        });

        kelvin.addActionListener(t -> {
            try {
                value = Double.parseDouble(countLabel.getText());
                resultTemperature = temperatureInterface.kelvin(value);
                resultCount();
            } catch (Exception exception) {
                errorConsole();
            }
        });

        fahrenheit.addActionListener(t -> {
            try {
                value = Double.parseDouble(countLabel.getText());
                resultTemperature = temperatureInterface.fahrenheit(value);
                resultCount();
            } catch (Exception exception) {
                errorConsole();
            }
        });
    }

    private void resultCount() {
        resultLabel.setText("Результат: " + resultTemperature + temperatureInterface.format());
    }

    private void errorConsole() {
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
}