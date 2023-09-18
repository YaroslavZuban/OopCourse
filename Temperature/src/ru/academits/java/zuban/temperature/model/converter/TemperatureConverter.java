package ru.academits.java.zuban.temperature.model.converter;

import ru.academits.java.zuban.temperature.model.scale.Scale;

import java.util.List;

public record TemperatureConverter(List<Scale> temperaturesConvertersList) implements Converter {
    @Override
    public List<Scale> getTemperaturesConvertersList() {
        return temperaturesConvertersList;
    }

    @Override
    public double convert(Scale fromScale, Scale toScale, double temperature) {
        double celsiusTemperature = fromScale.convertToCelsius(temperature);
        return toScale.convertFromCelsius(celsiusTemperature);
    }
}