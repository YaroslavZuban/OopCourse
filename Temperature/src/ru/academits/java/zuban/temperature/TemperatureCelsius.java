package ru.academits.java.zuban.temperature;

public class TemperatureCelsius implements Temperature {
    private String value;

    public double celsius(double temperature) {
        value = " C";
        return temperature;
    }

    @Override
    public String format() {
        return value;
    }

    public double fahrenheit(double temperature) {
        value = " Ф";
        return (temperature * 9 / 5 + 32);
    }

    public double kelvin(double temperature) {
        value = " K";
        return (temperature - 273.1);
    }
}