package ru.academits.java.zuban.Shape;

import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalDouble;

public class Triangle implements Shape {
    private double x1;
    private double y1;

    private double x2;
    private double y2;

    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double[] array = {x1, x2, x3};
        OptionalDouble max = Arrays.stream(array).max();
        OptionalDouble min = Arrays.stream(array).min();

        return max.getAsDouble() - min.getAsDouble();
    }

    @Override
    public double getHeight() {
        double[] array = {y1, y2, y3};
        OptionalDouble max = Arrays.stream(array).max();
        OptionalDouble min = Arrays.stream(array).min();

        return max.getAsDouble() - min.getAsDouble();
    }

    @Override
    public double getArea() {
        return 0.5 * ((x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3));
    }

    @Override
    public double getPerimeter() {
        double AB = lengthVector(x1, x2, y1, y2);
        double BC = lengthVector(x2, x3, y2, y3);
        double CA = lengthVector(x3, x1, y3, y1);

        return AB + BC + CA;
    }

    private double lengthVector(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 && Double.compare(triangle.y1, y1) == 0 && Double.compare(triangle.x2, x2) == 0 && Double.compare(triangle.y2, y2) == 0 && Double.compare(triangle.x3, x3) == 0 && Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", x3=" + x3 +
                ", y3=" + y3 +
                '}';
    }
}
