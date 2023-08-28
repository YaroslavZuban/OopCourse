package ru.academits.java.zuban.temperature.ui;

import ru.academits.java.zuban.temperature.translation.TemperatureConverter;

import java.util.List;

public class TemperatureTranslator {
    private final List<TemperatureConverter> temperatureConverterList;
    private double result;

    public TemperatureTranslator(List<TemperatureConverter> temperatureConverterList) {
        this.temperatureConverterList = temperatureConverterList;
    }

    public void setResult(String temperature) {
        result = Double.parseDouble(temperature);
    }

    public double getResult() {
        return result;
    }

    public void convertToCelsius(int index) {
        result = temperatureConverterList.get(index).convertToCelsius(result);
    }

    public void convertFromCelsius(int index) {
        result = temperatureConverterList.get(index).convertFromCelsius(result);
    }
}