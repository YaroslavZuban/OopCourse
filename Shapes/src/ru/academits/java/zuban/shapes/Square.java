package ru.academits.java.zuban.shapes;

import java.util.Objects;

public class Square implements Shape {
    private final double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return length;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return length * length;
    }

    @Override
    public double getPerimeter() {
        return 4 * length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return square.length==length;
    }

    @Override
    public String toString() {
        return "Square{ length = " + length + '}';
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        return (int) (prime + length);
    }
}