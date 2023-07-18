package ru.academits.java.zuban.main;

import ru.academits.java.zuban.matrix.Matrix;
import ru.academits.java.zuban.vector.Vector;

public class Main {
    public static void main(String[] args) throws Exception {
        double[][] array = new double[][]{{1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};

        Matrix matrix1 = new Matrix(array);
        Matrix matrix2 = new Matrix(array);

        System.out.println("toString: " + matrix1);

        matrix1.add(matrix2);
        System.out.println("add: " + matrix1);

        matrix1.subtraction(matrix2);
        System.out.println("sub: " + matrix1);

        System.out.println("determinant: " + matrix1.getDeterminant());

        Vector vector = new Vector(new double[]{2, 2, 2, 2, 2});

        System.out.println("multiplication vector: " + matrix1.multiplicationVector(vector));

        matrix1.multiplicationScalar(10);
        System.out.println("multiplication scalar: " + matrix1);

        Matrix sum = Matrix.add(matrix1, matrix2);
        System.out.println("static matrix add: " + sum);

        Matrix sub = Matrix.subtraction(matrix1, matrix2);
        System.out.println("static matrix subtraction: " + sub);

        Matrix mul = Matrix.multiplication(matrix1, matrix2);
        System.out.println("static matrix multiplication: " + mul);

        Matrix trans=new Matrix(new double[][]{{2, 2, 2, 2, 2}});
        trans.transposition();

        System.out.println("transposition: " + trans);
    }
}
