package ru.academits.java.zuban.temperature.view;

import ru.academits.java.zuban.temperature.model.converter.Converter;
import ru.academits.java.zuban.temperature.model.scale.Scale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalView {
    private final Converter temperatureConverter;
    private JTextField inputValue;
    private JFrame windowFrame;
    private JLabel showResult;
    private String result;
    private double resultTemperature;
    private String format;
    private int inputIndexScale;
    private int outputIndexScale;

    public GraphicalView(Converter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    private JPanel setupButtonsAndListeners(List<JButton> buttonList, List<Scale> converterList,
                                            boolean isInputIndexScale) {
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

            int indexScale = i;

            buttonList.get(i).addActionListener(e -> {
                if (isInputIndexScale) {
                    inputIndexScale = indexScale;
                } else {
                    outputIndexScale = indexScale;
                }

                setButtonBackground(indexScale, buttonList);

                try {
                    resultTemperature = temperatureConverter.convert(
                            converterList.get(inputIndexScale),
                            converterList.get(outputIndexScale),
                            Double.parseDouble(inputValue.getText()));

                    formatResult();
                    format = " " + converterList.get(indexScale).getMeasurementUnit();
                    showResult();
                } catch (NumberFormatException exception) {
                    showErrorWindow();
                }
            });

            panelButton.add(button);
        }

        return panelButton;
    }

    private void showResult() {
        showResult.setText("Результат равен: " + result + format);
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

    private void formatResult() {
        result = String.format("%.3f", resultTemperature);
    }

    private void showErrorWindow() {
        JOptionPane.showMessageDialog(windowFrame,
                "Пожалуйста, введите корректное числовое значение для выполнения данной операции.",
                "Ошибка",
                JOptionPane.PLAIN_MESSAGE);

        result = "0";
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            windowFrame = new JFrame("Перевод температуры");
            windowFrame.setVisible(true);
            windowFrame.setResizable(false);
            windowFrame.setLocationRelativeTo(null);
            windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windowFrame.pack();

            windowFrame.setSize(500, 300);
            windowFrame.setLayout(new GridLayout(6, 1, 20, 10));

            JLabel textLabel = new JLabel("Выберите исходную шкалу температуры:");
            textLabel.setVerticalAlignment(JLabel.CENTER);
            textLabel.setHorizontalAlignment(JLabel.CENTER);
            windowFrame.add(textLabel);

            List<Scale> temperatureConvertersList = temperatureConverter.getTemperaturesConvertersList();

            format = temperatureConvertersList.get(0).getMeasurementUnit();

            List<JButton> listButtonConvertToCelsius = new ArrayList<>(temperatureConvertersList.size());

            windowFrame.add(setupButtonsAndListeners(listButtonConvertToCelsius,
                    temperatureConvertersList,
                    true));

            JPanel panelGridInput = new JPanel();
            GridLayout gridInput = new GridLayout(1, 2, 30, 20);

            panelGridInput.setLayout(gridInput);
            JLabel textInput = new JLabel("Введите число:");
            textInput.setVerticalAlignment(JLabel.CENTER);
            textInput.setHorizontalAlignment(JLabel.RIGHT);
            panelGridInput.add(textInput);

            inputValue = new JTextField();
            inputValue.setText("0");
            inputValue.setHorizontalAlignment(JTextField.CENTER);
            panelGridInput.add(inputValue);

            windowFrame.add(panelGridInput);

            JLabel textOutputLabel = new JLabel("Выберите целевую шкалу температуры:");
            textOutputLabel.setVerticalAlignment(JLabel.CENTER);
            textOutputLabel.setHorizontalAlignment(JLabel.CENTER);
            windowFrame.add(textOutputLabel);

            List<JButton> listButtonConvertFromCelsius = new ArrayList<>(temperatureConvertersList.size());

            windowFrame.add(setupButtonsAndListeners(listButtonConvertFromCelsius,
                    temperatureConvertersList,
                    false));

            showResult = new JLabel();
            showResult.setVerticalAlignment(JLabel.CENTER);
            showResult.setHorizontalAlignment(JLabel.CENTER);
            showResult.setText("Результат равен: ");

            windowFrame.add(showResult);
        });
    }
}