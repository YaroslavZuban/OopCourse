package ru.academits.java.zuban.shapes_main;

import ru.academits.java.zuban.comparator.AreaComparator;
import ru.academits.java.zuban.comparator.PerimeterComparator;
import ru.academits.java.zuban.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] listShapes = {
                new Circle(200),
                new Rectangle(10, 3),
                new Square(8),
                new Triangle(2, 2, 5, 6, 7, 9),
                new Circle(10),
                new Rectangle(2, 1),
                new Square(5),
                new Triangle(3, 1, 2, -4, 10, 15)
        };

        Shape maxAreaShape = getShapeMaxArea(listShapes);
        System.out.println("Вывод фигуры с максимальной площадью: " + maxAreaShape);

        Shape maxSecondPerimeterShape = getShapeMaxSecondPerimeter(listShapes);
        System.out.println("Вывод фигуры второй по величине периметру:" + maxSecondPerimeterShape);
    }

    public static Shape getShapeMaxArea(Shape[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        AreaComparator areaComparator=new AreaComparator();
        Arrays.sort(array, areaComparator);

        return array[array.length - 1];
    }

    public static Shape getShapeMaxSecondPerimeter(Shape[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return null;
        }

        PerimeterComparator perimeterComparator=new PerimeterComparator();
        Arrays.sort(array, perimeterComparator);

        return array[array.length - 2];
    }
}