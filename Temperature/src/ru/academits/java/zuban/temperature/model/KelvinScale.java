package ru.academits.java.zuban.temperature.model;

public class KelvinScale implements Scale {
    @Override
    public String getMeasurementUnit() {
        return "K";
    }

    @Override
    public String getName() {
        return "Кельвин";
    }

    @Override
    public double convertToCelsius(String temperature) {
        return Double.parseDouble(temperature) - 273.15;
    }

    @Override
    public double convertFromCelsius(String celsiusTemperature) {
        return Double.parseDouble(celsiusTemperature) + 273.15;
    }
}