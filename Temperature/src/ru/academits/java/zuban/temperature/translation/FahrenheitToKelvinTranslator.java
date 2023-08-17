package ru.academits.java.zuban.temperature.translation;

public class FahrenheitToKelvinTranslator implements ScaleTranslator {
    @Override
    public double translate(double value) {
        return (value - 32) * ((double) 5 / 9);
    }
}