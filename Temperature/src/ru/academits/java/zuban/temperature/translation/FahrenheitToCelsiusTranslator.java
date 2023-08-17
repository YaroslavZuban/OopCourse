package ru.academits.java.zuban.temperature.translation;

public class FahrenheitToCelsiusTranslator implements ScaleTranslator {
    @Override
    public double translate(double value) {
        return (value + 459.67) + ((double) 5 / 9);
    }
}