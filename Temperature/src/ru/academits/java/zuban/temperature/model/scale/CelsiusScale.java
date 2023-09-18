package ru.academits.java.zuban.temperature.model.scale;

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
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature;
    }
}