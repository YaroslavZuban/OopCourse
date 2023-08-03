package ru.academits.java.zuban.temperature_main;

import ru.academits.java.zuban.temperature.Temperature;
import ru.academits.java.zuban.temperature.TemperatureCelsius;
import ru.academits.java.zuban.temperature.TemperatureFahrenheit;
import ru.academits.java.zuban.temperature.TemperatureKelvin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalInterface extends JFrame {
    private Temperature temperatureInterface;
    private Double resultTemperature;
    private Double number;
    private final JButton kelvin;
    private final JButton celsius;
    private final JButton fahrenheit;
    private final JLabel result;
    private final JTextField countLabel;
    private final JLabel text;
    private final JPanel panel;
    private final JPanel panelIsTemperature;
    private boolean isFirstStart = false;

    private final JButton isFahrenheit;
    private final JButton isKelvin;
    private final JButton isCelsius;

    public GraphicalInterface() {
        super("Перевод температуры");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        text = new JLabel("Перевод в");
        countLabel = new JTextField("0", 15);

        kelvin = new JButton("Кельвин");
        celsius = new JButton("Цельсий");
        fahrenheit = new JButton("Фаренгейт");

        result = new JLabel("Результат: " + 0 + " C");

        isFahrenheit = new JButton("в Фаренгейт");
        isKelvin = new JButton("в Кельвин");
        isCelsius = new JButton("в Цельсий");
        panel = new JPanel(new FlowLayout());
        panelIsTemperature = new JPanel(new FlowLayout());
        temperatureInterface = new TemperatureCelsius();

        setIconImage(new ImageIcon("C:\\Users\\ben70\\IdeaProjects\\Popitka\\src\\termo.png").getImage());

        try {
            number = Double.parseDouble(countLabel.getText());
        } catch (Exception exception) {

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
        panel.add(text);

        panel.add(celsius);
        panel.add(kelvin);
        panel.add(fahrenheit);

        add(result, BorderLayout.PAGE_END);
        add(panel, BorderLayout.CENTER);
    }

    private JDialog createDialog(String title, boolean modal) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(180, 90);
        return dialog;
    }

    private void set() {
        celsius.addActionListener(t -> {
            try {
                number = Double.parseDouble(countLabel.getText());
                resultTemperature = temperatureInterface.celsius(number);
                resultCount();
            } catch (Exception exception) {
                errorConsole();
            }
        });

        kelvin.addActionListener(t -> {
            try {
                number = Double.parseDouble(countLabel.getText());
                resultTemperature = temperatureInterface.kelvin(number);
                resultCount();
            } catch (Exception exception) {
                errorConsole();
            }
        });

        fahrenheit.addActionListener(t -> {
            try {
                number = Double.parseDouble(countLabel.getText());
                resultTemperature = temperatureInterface.fahrenheit(number);
                resultCount();
            } catch (Exception exception) {
                errorConsole();
            }
        });
    }

    private void resultCount() {
        result.setText("Результат: " + resultTemperature + temperatureInterface.format());
    }

    private void errorConsole() {
        JDialog dialog = createDialog("Ошибка", true);
        dialog.setLocationRelativeTo(null);

        JLabel l = new JLabel("     Не верное значение");
        JButton b = new JButton("ок");

        dialog.add(l, BorderLayout.NORTH);

        b.addActionListener(e -> {
            dialog.setVisible(false);
        });

        b.setSize(20, 10);
        dialog.add(b, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        GraphicalInterface h = new GraphicalInterface();
        h.setVisible(true);
        h.pack();
    }
}