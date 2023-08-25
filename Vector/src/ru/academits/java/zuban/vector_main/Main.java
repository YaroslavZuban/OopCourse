package ru.academits.java.zuban.vector_main;

import ru.academits.java.zuban.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(new double[]{3, 4, 5, 6, 7, 8, 9});

        vector1.add(vector2);
        System.out.println("vector1 add: " + vector1);

        vector1.subtract(vector2);
        System.out.println("vector1 subtraction: " + vector1);

        vector2.multiplyByScalar(2);
        System.out.println("vector2 multiplyVectorScalar: " + vector2);

        vector2.reverse();
        System.out.println("vector2 revers: " + vector2);

        System.out.println("vector2 length: " + vector2.getLength());

        Vector result = Vector.getSum(vector1, vector2);
        System.out.println("static vector1 add: " + result);

        result = Vector.getDifference(vector1, vector2);
        System.out.println("static vector1 subtraction: " + result);

        System.out.println("static vector1 scalar: " + Vector.getDotProduct(vector1, vector2));
    }
}