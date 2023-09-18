package ru.academits.java.zuban.temperature.model.scale;

public interface Scale {
    String getMeasurementUnit();

    String getName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double celsiusTemperature);
}