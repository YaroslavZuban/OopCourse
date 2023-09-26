package ru.academits.java.zuban.temperature.model.converter;

import ru.academits.java.zuban.temperature.model.scales.Scale;

import java.util.List;

public interface Converter {
    double convert(Scale fromScale, Scale toScale, double temperature);

    List<Scale> getTemperaturesConvertersList();
}