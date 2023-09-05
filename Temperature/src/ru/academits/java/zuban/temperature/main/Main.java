package ru.academits.java.zuban.temperature.main;

import ru.academits.java.zuban.temperature.model.CelsiusScale;
import ru.academits.java.zuban.temperature.model.FahrenheitScale;
import ru.academits.java.zuban.temperature.model.KelvinScale;
import ru.academits.java.zuban.temperature.model.Scale;
import ru.academits.java.zuban.temperature.representation.TemperatureConverter;
import ru.academits.java.zuban.temperature.ui.GraphicalView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> listTemperatureConverter = new ArrayList<>();

        listTemperatureConverter.add(new CelsiusScale());
        listTemperatureConverter.add(new FahrenheitScale());
        listTemperatureConverter.add(new KelvinScale());

        TemperatureConverter temperatureConverter = new TemperatureConverter(listTemperatureConverter);

        SwingUtilities.invokeLater(() -> new GraphicalView(listTemperatureConverter, temperatureConverter));
    }
}