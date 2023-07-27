package ru.academits.java.zuban.matrix_main;

import ru.academits.java.zuban.matrix.Matrix;
import ru.academits.java.zuban.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] array = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}};

        Matrix matrix1 = new Matrix(array);
        Matrix matrix2 = new Matrix(array);

        System.out.println("toString: " + matrix1);

        matrix1.add(matrix2);
        System.out.println("add: " + matrix1);

        matrix1.subtract(matrix2);
        System.out.println("sub: " + matrix1);

        System.out.println("determinant: " + matrix1.getDeterminant());

        Vector vector = new Vector(new double[]{2, 2, 2, 2, 2});

        System.out.println("multiplication vector: " + matrix1.getMultiplyVector(vector));

        matrix1.multiplyScalar(10);
        System.out.println("multiplication scalar: " + matrix1);

        Matrix matrixSum = Matrix.getSum(matrix1, matrix2);
        System.out.println("static matrix add: " + matrixSum);

        Matrix matrixSubtract = Matrix.getDifference(matrix1, matrix2);
        System.out.println("static matrix subtraction: " + matrixSubtract);

        Matrix matrixMultiplication = Matrix.getWork(matrix1, matrix2);
        System.out.println("static matrix multiplication: " + matrixMultiplication);

        Matrix matrixTransposed = new Matrix(new double[][]{{2, 2, 2, 2, 2}});
        System.out.println("no transposition: " + matrixTransposed);

        matrixTransposed.transpose();

        System.out.println("transposition: " + matrixTransposed);
    }
}