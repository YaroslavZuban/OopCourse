package ru.academits.java.zuban.temperature.model;

public class FahrenheitScale implements Scale {
    @Override
    public String getMeasurementUnit() {
        return "F";
    }

    @Override
    public String getName() {
        return "Фаренгейт";
    }

    @Override
    public double convertToCelsius(String temperature) {
        return (Double.parseDouble(temperature) - 32) * 5.0 / 9;
    }

    @Override
    public double convertFromCelsius(String celsiusTemperature) {
        return Double.parseDouble(celsiusTemperature) * (9.0 / 5) + 32;
    }
}