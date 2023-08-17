package ru.academits.java.zuban.temperature.translation;

public class KelvinToCelsiusTranslator implements ScaleTranslator {
    @Override
    public double translate(double value) {
        return value - 273.15;
    }
}