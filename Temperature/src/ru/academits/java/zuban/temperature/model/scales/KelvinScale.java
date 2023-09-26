package ru.academits.java.zuban.temperature.model.scales;

public class KelvinScale implements Scale {
    @Override
    public String getMeasurementUnit() {
        return "K";
    }

    @Override
    public String getName() {
        return "Кельвин";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }
}