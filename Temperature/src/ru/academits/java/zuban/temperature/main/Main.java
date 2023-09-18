package ru.academits.java.zuban.temperature.main;

import ru.academits.java.zuban.temperature.model.converter.Converter;
import ru.academits.java.zuban.temperature.model.scale.CelsiusScale;
import ru.academits.java.zuban.temperature.model.scale.FahrenheitScale;
import ru.academits.java.zuban.temperature.model.scale.KelvinScale;
import ru.academits.java.zuban.temperature.model.scale.Scale;
import ru.academits.java.zuban.temperature.model.converter.TemperatureConverter;
import ru.academits.java.zuban.temperature.view.GraphicalView;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> listTemperatureConverter = Arrays.asList(new CelsiusScale(), new FahrenheitScale(), new KelvinScale());

        Converter converter = new TemperatureConverter(listTemperatureConverter);

        new GraphicalView(converter).run();
    }
}