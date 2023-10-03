package ru.academits.java.zuban.temperature.model.converter;

import ru.academits.java.zuban.temperature.model.scales.Scale;

import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<Scale> scales;

    public TemperatureConverter(List<Scale> scales) {
        this.scales = scales;
    }

    @Override
    public List<Scale> getScalesList() {
        return scales;
    }

    @Override
    public double convert(Scale fromScale, Scale toScale, double temperature) {
        double celsiusTemperature = fromScale.convertToCelsius(temperature);
        return toScale.convertFromCelsius(celsiusTemperature);
    }
}