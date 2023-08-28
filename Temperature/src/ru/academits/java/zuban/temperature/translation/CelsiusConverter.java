package ru.academits.java.zuban.temperature.translation;

public class CelsiusConverter implements TemperatureConverter {
    @Override
    public String getType() {
        return "C";
    }

    @Override
    public String getName() {
        return "Цельсий";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature;
    }
}