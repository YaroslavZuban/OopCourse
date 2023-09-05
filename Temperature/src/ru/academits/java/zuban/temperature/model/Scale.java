package ru.academits.java.zuban.temperature.model;

public interface Scale {
    String getMeasurementUnit();

    String getName();

    double convertToCelsius(String temperature);

    double convertFromCelsius(String celsiusTemperature);
}