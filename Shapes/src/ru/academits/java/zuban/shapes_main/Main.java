package ru.academits.java.zuban.shapes_main;

import ru.academits.java.zuban.shapes_comparators.ShapeAreaComparator;
import ru.academits.java.zuban.shapes_comparators.ShapePerimeterComparator;
import ru.academits.java.zuban.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] figures = {
                new Circle(200),
                new Rectangle(10, 3),
                new Square(8),
                new Triangle(2, 2, 5, 6, 7, 9),
                new Circle(10),
                new Rectangle(2, 1),
                new Square(5),
                new Triangle(3, 1, 2, -4, 10, 15)
        };

        Shape maxAreaShape = getMaxAreaShape(figures);
        System.out.println("Вывод фигуры с максимальной площадью: " + maxAreaShape);

        Shape maxSecondPerimeterShape = getMaxSecondPerimeterShape(figures);
        System.out.println("Вывести фигуру, у которой второй по величине периметр: " + maxSecondPerimeterShape);
    }

    public static Shape getMaxAreaShape(Shape[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        ShapeAreaComparator areaComparator = new ShapeAreaComparator();
        Arrays.sort(array, areaComparator);

        return array[array.length - 1];
    }

    public static Shape getMaxSecondPerimeterShape(Shape[] array) {
        if (array == null || array.length <= 1) {
            return null;
        }

        ShapePerimeterComparator perimeterComparator = new ShapePerimeterComparator();
        Arrays.sort(array, perimeterComparator);

        return array[array.length - 2];
    }
}