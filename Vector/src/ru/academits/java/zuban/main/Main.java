package ru.academits.java.zuban.main;

import ru.academits.java.zuban.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(5);
        Vector temp = new Vector(new double[]{3, 4, 5, 6, 7, 8, 9});

        vector.add(temp);
        System.out.println("vector add: " + vector);

        vector.subtraction(temp);
        System.out.println("vector subtraction: " + vector);

        temp.multiplyVectorScalar(2);
        System.out.println("temp multiplyVectorScalar: " + temp);

        temp.revers();
        System.out.println("temp revers: " + temp);

        System.out.println("temp length: " + temp.length());

        Vector result = Vector.add(vector, temp);
        System.out.println("static vector add: " + result);

        result = Vector.subtraction(vector, temp);
        System.out.println("static vector subtraction: " + result);

        System.out.println("static vector scalar: " + Vector.scalar(vector, temp));
    }
}
