package ru.academits.java.zuban.temperature;

public class TemperatureCelsius implements Temperature {
    private String meaning;

    public double celsius(double temperature) {
        meaning = " C";
        return temperature;
    }

    @Override
    public String format() {
        return meaning;
    }

    public double fahrenheit(double temperature) {
        meaning = " Ф";
        return (temperature * 9 / 5 + 32);
    }

    public double kelvin(double temperature) {
        meaning = " K";
        return (temperature - 273.1);
    }
}