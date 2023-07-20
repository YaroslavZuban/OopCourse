package ru.academits.java.zuban.comparator;

import ru.academits.java.zuban.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double perimeterShape1 = shape1.getPerimeter();
        double perimeterShape2 = shape2.getPerimeter();

        if (perimeterShape1 < perimeterShape2) {
            return -1;
        } else if (perimeterShape1 > perimeterShape2) {
            return 1;
        }

        return 0;
    }
}
