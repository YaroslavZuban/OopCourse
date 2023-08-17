package ru.academits.java.zuban.temperature.translation;

public class KelvinToFahrenheitTranslator implements ScaleTranslator {
    @Override
    public double translate(double value) {
        return value * ((double) 9 / 5) - 459.67;
    }
}