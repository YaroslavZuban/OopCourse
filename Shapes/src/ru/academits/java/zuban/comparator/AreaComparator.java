package ru.academits.java.zuban.comparator;

import ru.academits.java.zuban.shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double areaShape1 = shape1.getArea();
        double areaShape2 = shape2.getArea();

        if (areaShape1 < areaShape2) {
            return -1;
        } else if (areaShape1 > areaShape2) {
            return 1;
        }

        return 0;
    }
}
