package ru.academits.java.zuban.temperature.representation;

import ru.academits.java.zuban.temperature.model.Scale;

import java.util.List;

public class TemperatureConverter {
    private final List<Scale> temperaturesConverterList;
    private String inputValue;
    private String outputValue;

    public TemperatureConverter(List<Scale> temperaturesConverterList) {
        this.temperaturesConverterList = temperaturesConverterList;
    }

    public void setInputValue(String temperature) {
        inputValue = temperature;
    }

    public String getOutputValue() {
        return outputValue;
    }

    public void convertToCelsius(int index) {
        outputValue = String.valueOf(temperaturesConverterList.get(index).convertToCelsius(inputValue));
    }

    public void convertFromCelsius(int index) {
        outputValue = String.valueOf(temperaturesConverterList.get(index).convertFromCelsius(outputValue));
    }
}