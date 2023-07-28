package ru.academits.java.zuban.shapes_comparators;

import ru.academits.java.zuban.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double shapePerimeter1 = shape1.getPerimeter();
        double shapePerimeter2 = shape2.getPerimeter();

        return Double.compare(shapePerimeter1, shapePerimeter2);
    }
}