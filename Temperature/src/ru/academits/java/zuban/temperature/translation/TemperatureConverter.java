package ru.academits.java.zuban.temperature.translation;

public interface TemperatureConverter {
    String getType();

    String getName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double celsiusTemperature);
}