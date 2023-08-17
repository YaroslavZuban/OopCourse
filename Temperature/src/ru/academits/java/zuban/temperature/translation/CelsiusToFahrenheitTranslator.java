package ru.academits.java.zuban.temperature.translation;

public class CelsiusToFahrenheitTranslator implements ScaleTranslator {
    @Override
    public double translate(double value) {
        return value * ((double) 9 / 5) + 32;
    }
}