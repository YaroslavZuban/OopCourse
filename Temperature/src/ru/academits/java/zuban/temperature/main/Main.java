package ru.academits.java.zuban.temperature.main;

import ru.academits.java.zuban.temperature.model.converter.Converter;
import ru.academits.java.zuban.temperature.model.scales.CelsiusScale;
import ru.academits.java.zuban.temperature.model.scales.FahrenheitScale;
import ru.academits.java.zuban.temperature.model.scales.KelvinScale;
import ru.academits.java.zuban.temperature.model.scales.Scale;
import ru.academits.java.zuban.temperature.model.converter.TemperatureConverter;
import ru.academits.java.zuban.temperature.view.GraphicalView;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> scales = Arrays.asList(new CelsiusScale(), new FahrenheitScale(), new KelvinScale());

        Converter converter = new TemperatureConverter(scales);

        new GraphicalView(converter).run();
    }
}