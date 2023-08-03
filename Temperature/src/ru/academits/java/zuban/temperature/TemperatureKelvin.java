package ru.academits.java.zuban.temperature;

public class TemperatureKelvin implements Temperature {
    private String meaning;

    @Override
    public double fahrenheit(double temperature) {
        meaning = " Ф";
        return 1.8 * (temperature - 273.15) + 32;
    }

    @Override
    public double kelvin(double temperature) {
        meaning = " K";
        return temperature;
    }

    @Override
    public double celsius(double temperature) {
        meaning = " C";
        return temperature + 273.15;
    }

    @Override
    public String format() {
        return meaning;
    }
}