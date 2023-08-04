package ru.academits.java.zuban.temperature;

public class TemperatureFahrenheit implements Temperature {
    private String value;

    @Override
    public double fahrenheit(double temperature) {
        value = " Ф";
        return temperature;
    }

    @Override
    public double kelvin(double temperature) {
        value = " K";
        return (5 * (temperature - 32) / 9 + 273.15);
    }

    @Override
    public double celsius(double temperature) {
        value = " C";
        return (9 * (temperature + 32) / 5);
    }

    @Override
    public String format() {
        return value;
    }
}