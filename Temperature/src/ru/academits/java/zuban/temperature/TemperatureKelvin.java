package ru.academits.java.zuban.temperature;

public class TemperatureKelvin implements Temperature {
    private String value;

    @Override
    public double fahrenheit(double temperature) {
        value = " Ф";
        return 1.8 * (temperature - 273.15) + 32;
    }

    @Override
    public double kelvin(double temperature) {
        value = " K";
        return temperature;
    }

    @Override
    public double celsius(double temperature) {
        value = " C";
        return temperature + 273.15;
    }

    @Override
    public String format() {
        return value;
    }
}