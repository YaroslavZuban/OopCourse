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
    private double outputTemperature;
    private String measurementUnit;
    private int inputScaleIndex;
    private int outputScaleIndex;

    public GraphicalView(Converter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    private JPanel setupButtonsAndListeners(List<JButton> buttonsList,
                                            List<Scale> scalesList,
                                            boolean isInputScale) {
        JPanel buttonsPanel = new JPanel();
        GridLayout buttonsGrid = new GridLayout(1, scalesList.size(), 20, 20);
        buttonsPanel.setLayout(buttonsGrid);

        for (int i = 0; i < scalesList.size(); i++) {
            JButton button = new JButton(scalesList.get(i).getName());

            if (i == 0) {
                button.setBackground(Color.GREEN);
            } else {
                button.setBackground(Color.GRAY);
            }

            buttonsList.add(button);

            int scaleIndex = i;

            buttonsList.get(i).addActionListener(x -> {
                if (isInputScale) {
                    inputScaleIndex = scaleIndex;
                } else {
                    outputScaleIndex = scaleIndex;
                }

                setButtonsBackground(scaleIndex, buttonsList);

                try {
                    outputTemperature = temperatureConverter.convert(
                            scalesList.get(inputScaleIndex),
                            scalesList.get(outputScaleIndex),
                            Double.parseDouble(inputTemperatureTextField.getText()));

                    measurementUnit = " " + scalesList.get(scaleIndex).getMeasurementUnit();
                    setTemperatureLabel();
                } catch (NumberFormatException e) {
                    showErrorWindow();
                }
            });

            buttonsPanel.add(button);
        }

        return buttonsPanel;
    }

    private void setTemperatureLabel() {
        String outputTemperatureLine = String.format("%.3f", outputTemperature);
        outputTemperatureLabel.setText("Результат равен: " + outputTemperatureLine + measurementUnit);
    }

    private static void setButtonsBackground(int shadedButtonIndex, List<JButton> buttonList) {
        for (int i = 0; i < buttonList.size(); i++) {
            if (shadedButtonIndex == i) {
                buttonList.get(i).setBackground(Color.GREEN);
            } else {
                buttonList.get(i).setBackground(Color.GRAY);
            }
        }
    }

    private void showErrorWindow() {
        JOptionPane.showMessageDialog(windowFrame,
                "Пожалуйста, введите корректное числовое значение для выполнения данной операции.",
                "Ошибка",
                JOptionPane.PLAIN_MESSAGE);
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

            List<Scale> scales = temperatureConverter.getScalesList();

            measurementUnit = scales.get(0).getMeasurementUnit();

            List<JButton> buttonsForConversionToCelsius = new ArrayList<>(scales.size());

            windowFrame.add(setupButtonsAndListeners(buttonsForConversionToCelsius,
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

            List<JButton> buttonsForConversionFromCelsius = new ArrayList<>(scales.size());

            windowFrame.add(setupButtonsAndListeners(buttonsForConversionFromCelsius,
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