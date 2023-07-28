package ru.academits.java.zuban.shapes_comparators;

import ru.academits.java.zuban.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double shapeArea1 = shape1.getArea();
        double shapeArea2 = shape2.getArea();

        return Double.compare(shapeArea1, shapeArea2);
    }
}