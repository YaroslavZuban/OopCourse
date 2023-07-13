package ru.academits.java.zuban.Shape;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] array = new Shape[]{new Circle(200), new Rectangle(10, 3),
                new Square(8), new Triangle(2, 2, 5, 6, 7, 9),
                new Circle(10), new Rectangle(2, 1),
                new Square(5), new Triangle(3, 1, 2, -4, 10, 15)};

        Shape resultMaxShapeArea = shapeMaxArea(array);
        System.out.println(resultMaxShapeArea.toString());

        Shape resultMaxTwoShapePerimeter = shapeMaxTwoPerimeter(array);
        System.out.println(resultMaxTwoShapePerimeter.toString());
    }

    public static Shape shapeMaxArea(Shape[] array) {
        Arrays.sort(array, Comparator.comparingDouble(Shape::getArea));
        return array[array.length - 1];
    }

    public static Shape shapeMaxTwoPerimeter(Shape[] array) {
        Arrays.sort(array, Comparator.comparingDouble(Shape::getPerimeter));
        return array[array.length - 2];
    }
}
