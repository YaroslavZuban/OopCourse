package ru.academits.java.zuban.temperature.model;

public class CelsiusScale implements Scale {
    @Override
    public String getMeasurementUnit() {
        return "C";
    }

    @Override
    public String getName() {
        return "Цельсий";
    }

    @Override
    public double convertToCelsius(String temperature) {
        return Double.parseDouble(temperature);
    }

    @Override
    public double convertFromCelsius(String celsiusTemperature) {
        return Double.parseDouble(celsiusTemperature);
    }
}