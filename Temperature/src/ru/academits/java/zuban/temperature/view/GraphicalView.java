package ru.academits.java.zuban.temperature.view;

import ru.academits.java.zuban.temperature.model.converter.Converter;
import ru.academits.java.zuban.temperature.model.scales.Scale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalView {
    private final Converter temperatureConverter;
    private JTextField inputTemperatureTextField;
    private JFrame windowFrame;
    private JLabel outputTemperatureLabel;
    private String outputTemperatureLine;
    private double outputTemperature;
    private String unitMeasurement;
    private int inputScaleIndex;
    private int outputScaleIndex;

    public GraphicalView(Converter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    private JPanel setupButtonsAndListeners(List<JButton> buttonList,
                                            List<Scale> converterList,
                                            boolean isInputIndexScale) {
        JPanel buttonsPanel = new JPanel();
        GridLayout buttonGrid = new GridLayout(1, converterList.size(), 20, 20);
        buttonsPanel.setLayout(buttonGrid);

        for (int i = 0; i < converterList.size(); i++) {
            JButton button = new JButton(converterList.get(i).getName());

            if (i == 0) {
                button.setBackground(Color.GREEN);
            } else {
                button.setBackground(Color.GRAY);
            }

            buttonList.add(button);

            int indexScale = i;

            buttonList.get(i).addActionListener(x -> {
                if (isInputIndexScale) {
                    inputScaleIndex = indexScale;
                } else {
                    outputScaleIndex = indexScale;
                }

                setButtonsBackground(indexScale, buttonList);

                try {
                    outputTemperature = temperatureConverter.convert(
                            converterList.get(inputScaleIndex),
                            converterList.get(outputScaleIndex),
                            Double.parseDouble(inputTemperatureTextField.getText()));

                    formatResult();
                    unitMeasurement = " " + converterList.get(indexScale).getMeasurementUnit();
                    outputTemperatureLabel();
                } catch (NumberFormatException e) {
                    showErrorWindow();
                }
            });

            buttonsPanel.add(button);
        }

        return buttonsPanel;
    }

    private void outputTemperatureLabel() {
        outputTemperatureLabel.setText("Результат равен: " + outputTemperatureLine + unitMeasurement);
    }

    private static void setButtonsBackground(int buttonShadedIndex, List<JButton> listButton) {
        for (int i = 0; i < listButton.size(); i++) {
            if (buttonShadedIndex == i) {
                listButton.get(i).setBackground(Color.GREEN);
            } else {
                listButton.get(i).setBackground(Color.GRAY);
            }
        }
    }

    private void formatResult() {
        outputTemperatureLine = String.format("%.3f", outputTemperature);
    }

    private void showErrorWindow() {
        JOptionPane.showMessageDialog(windowFrame,
                "Пожалуйста, введите корректное числовое значение для выполнения данной операции.",
                "Ошибка",
                JOptionPane.PLAIN_MESSAGE);

        outputTemperatureLine = "0";
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

            List<Scale> scales = temperatureConverter.getTemperaturesConvertersList();

            unitMeasurement = scales.get(0).getMeasurementUnit();

            List<JButton> listButtonConvertToCelsius = new ArrayList<>(scales.size());

            windowFrame.add(setupButtonsAndListeners(listButtonConvertToCelsius,
                    scales,
                    true));

            JPanel gridInputPanel = new JPanel();
            GridLayout inputGridLayout = new GridLayout(1, 2, 30, 20);

            gridInputPanel.setLayout(inputGridLayout);
            JLabel textInputLabel = new JLabel("Введите число:");
            textInputLabel.setVerticalAlignment(JLabel.CENTER);
            textInputLabel.setHorizontalAlignment(JLabel.RIGHT);
            gridInputPanel.add(textInputLabel);

            inputTemperatureTextField = new JTextField();
            inputTemperatureTextField.setText("0");
            inputTemperatureTextField.setHorizontalAlignment(JTextField.CENTER);
            gridInputPanel.add(inputTemperatureTextField);

            windowFrame.add(gridInputPanel);

            JLabel textOutputLabel = new JLabel("Выберите целевую шкалу температуры:");
            textOutputLabel.setVerticalAlignment(JLabel.CENTER);
            textOutputLabel.setHorizontalAlignment(JLabel.CENTER);
            windowFrame.add(textOutputLabel);

            List<JButton> listButtonConvertFromCelsius = new ArrayList<>(scales.size());

            windowFrame.add(setupButtonsAndListeners(listButtonConvertFromCelsius,
                    scales,
                    false));

            outputTemperatureLabel = new JLabel();
            outputTemperatureLabel.setVerticalAlignment(JLabel.CENTER);
            outputTemperatureLabel.setHorizontalAlignment(JLabel.CENTER);
            outputTemperatureLabel.setText("Результат равен: ");

            windowFrame.add(outputTemperatureLabel);
        });
    }
}