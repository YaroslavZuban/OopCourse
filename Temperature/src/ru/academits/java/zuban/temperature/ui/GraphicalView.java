package ru.academits.java.zuban.temperature.ui;

import ru.academits.java.zuban.temperature.translation.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalView {
    private final TemperatureTranslator temperatureTranslator;
    private final JTextField valueInput;
    private final JFrame windowFrame;
    private final JLabel labelOutput;
    private String result;
    private String format = " C";
    private int valueUniqueInput = 0;
    private int valueUniqueOutput = 0;

    public GraphicalView() {
        windowFrame = new JFrame("Перевод температуры");
        windowFrame.setVisible(true);
        windowFrame.setResizable(false);
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.pack();

        windowFrame.setSize(500, 300);
        windowFrame.setLayout(new GridLayout(6, 1, 20, 10));

        JLabel textLabel = new JLabel("Выберите исходную температуру:");
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        windowFrame.add(textLabel);

        List<TemperatureConverter> listTemperatureConverter = new ArrayList<>();

        listTemperatureConverter.add(new CelsiusConverter());
        listTemperatureConverter.add(new FahrenheitConverter());
        listTemperatureConverter.add(new KelvinConverter());

        temperatureTranslator = new TemperatureTranslator(listTemperatureConverter);
        List<JButton> listButtonConvertToCelsius = new ArrayList<>(listTemperatureConverter.size());
        windowFrame.add(setupButtonsAndListeners(listButtonConvertToCelsius, listTemperatureConverter, true));

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

        List<JButton> listButtonConvertFromCelsius = new ArrayList<>(listTemperatureConverter.size());
        windowFrame.add(setupButtonsAndListeners(listButtonConvertFromCelsius, listTemperatureConverter, false));

        labelOutput = new JLabel();
        labelOutput.setVerticalAlignment(JLabel.CENTER);
        labelOutput.setHorizontalAlignment(JLabel.CENTER);
        labelOutput.setText("Результат равен: ");

        windowFrame.add(labelOutput);
    }

    private JPanel setupButtonsAndListeners(List<JButton> buttonList, List<TemperatureConverter> converterList,
                                            boolean isValueUniqueInput) {
        JPanel panelButton = new JPanel();
        GridLayout buttonGrid = new GridLayout(1, converterList.size(), 20, 20);
        panelButton.setLayout(buttonGrid);

        for (int i = 0; i < converterList.size(); i++) {
            JButton button = new JButton(converterList.get(i).getName());

            if (i == 0) {
                button.setBackground(Color.GREEN);
            } else {
                button.setBackground(Color.GRAY);
            }

            buttonList.add(button);

            int finalI = i;

            buttonList.get(i).addActionListener(e -> {
                if (isValueUniqueInput) {
                    valueUniqueInput = finalI;
                } else {
                    valueUniqueOutput = finalI;
                }

                setButtonBackground(finalI, buttonList);

                try {
                    temperatureTranslator.setResult(valueInput.getText());
                    temperatureTranslator.convertToCelsius(valueUniqueInput);
                    temperatureTranslator.convertFromCelsius(valueUniqueOutput);

                    formatNumber();
                    format = " " + converterList.get(finalI).getType();
                    labelOutput();
                } catch (NumberFormatException exception) {
                    error();
                }
            });

            panelButton.add(button);
        }

        return panelButton;
    }

    private void labelOutput() {
        labelOutput.setText("Результат равен: " + result + format);
    }

    private static void setButtonBackground(int index, List<JButton> listButton) {
        for (int i = 0; i < listButton.size(); i++) {
            if (index == i) {
                listButton.get(i).setBackground(Color.GREEN);
            } else {
                listButton.get(i).setBackground(Color.GRAY);
            }
        }
    }

    private void formatNumber() {
        result = String.valueOf(temperatureTranslator.getResult());
        result = String.format("%.3f", Double.parseDouble(result));
    }

    private void error() {
        JOptionPane.showMessageDialog(windowFrame,
                "Введены некорректные символы. Пожалуйста, используйте только допустимые символы для выполнения данной операции.",
                "Ошибка",
                JOptionPane.PLAIN_MESSAGE);

        valueInput.setText("0");
        result = "0";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphicalView::new);
    }
}