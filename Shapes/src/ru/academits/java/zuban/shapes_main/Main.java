package ru.academits.java.zuban.shapes_main;

import ru.academits.java.zuban.shapes_comparators.ShapeAreaComparator;
import ru.academits.java.zuban.shapes_comparators.ShapePerimeterComparator;
import ru.academits.java.zuban.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(200),
                new Rectangle(10, 3),
                new Square(8),
                new Triangle(2, 2, 5, 6, 7, 9),
                new Circle(10),
                new Rectangle(2, 1),
                new Square(5),
                new Triangle(3, 1, 2, -4, 10, 15)
        };

        Shape maxAreaShape = getMaxAreaShape(shapes);
        System.out.println("Фигура с максимальной площадью: " + maxAreaShape);

        Shape secondMaxdPerimeterShape = getSecondMaxPerimeterShape(shapes);
        System.out.println("Фигура с вторым по величине периметром: " + secondMaxdPerimeterShape);
    }

    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        if (shapes == null || shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }
}