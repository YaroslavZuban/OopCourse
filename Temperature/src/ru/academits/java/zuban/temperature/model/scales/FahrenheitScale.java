package ru.academits.java.zuban.temperature.model.scales;

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
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * 5.0 / 9;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature * (9.0 / 5) + 32;
    }
}