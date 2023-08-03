package ru.academits.java.zuban.temperature;

public interface Temperature {
    double fahrenheit(double temperature);

    double kelvin(double temperature);

    double celsius(double temperature);

    String format();
}
