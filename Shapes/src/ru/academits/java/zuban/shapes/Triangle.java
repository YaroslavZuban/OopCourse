package ru.academits.java.zuban.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;

    private final double x2;
    private final double y2;

    private final double x3;
    private final double y3;

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
        double minX = Math.min(x1, Math.min(x2, x3));
        double maxX = Math.max(x1, Math.max(x2, x3));

        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double minY = Math.min(y1, Math.min(y2, y3));
        double maxY = Math.max(y1, Math.max(y2, y3));

        return maxY - minY;
    }

    @Override
    public double getArea() {
        return 0.5 * ((x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3));
    }

    @Override
    public double getPerimeter() {
        double side1 = getVectorLength(x1, y1, x2, y2);
        double side2 = getVectorLength(x2, y2, x3, y3);
        double side3 = getVectorLength(x3, y3, x1, y1);

        return side1 + side2 + side3;
    }

    private static double getVectorLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return triangle.x1 == x1 && triangle.y1 == y1 && triangle.x2 == x2 && triangle.y2 == y2 && triangle.x3 == x3 && triangle.y3 == y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);

        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);

        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }

    @Override
    public String toString() {
        return "Triangle{ " +
                "x1 = " + x1 +
                ", y1 = " + y1 +
                ", x2 = " + x2 +
                ", y2 = " + y2 +
                ", x3 = " + x3 +
                ", y3 = " + y3 +
                '}';
    }
}