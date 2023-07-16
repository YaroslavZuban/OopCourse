package ru.academits.java.zuban.matrix;


import ru.academits.java.zuban.vector.Vector;

import java.util.Arrays;
import java.util.IllformedLocaleException;

public class Matrix {
    private Vector[] matrix;
    private double determinant;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllformedLocaleException();
        }

        matrix = new Vector[n];

        for (Vector element : matrix) {
            element = new Vector(m);
        }
    }

    public Matrix(Matrix array) {
        if (array == null) {
            throw new NullPointerException();
        }

        matrix = new Vector[array.matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new Vector(array.matrix[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException();
        }

        matrix = new Vector[array.length];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] array) {
        if (array == null) {
            throw new NullPointerException();
        }

        matrix = new Vector[array.length];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new Vector(array[i]);
        }
    }

    public Vector[] getMatrix() {
        return matrix;
    }

    public void setMatrix(Vector[] matrix) {
        this.matrix = matrix;
    }

    public int getSizeColumns() {
        return matrix[0].getSize();
    }

    public int getSizeLine() {
        return matrix.length;
    }

    public Vector getLine(int index) {
        if (index >= matrix.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return matrix[index];
    }

    public void setLine(Vector array, int index) {
        if (index >= matrix.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (array == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < matrix[index].getSize(); i++) {
            if (array.getSize() > i) {
                matrix[index].setElement(i, array.getElement(i));
            } else {
                matrix[index].setElement(i, 0);
            }
        }
    }

    public Vector getColumn(int index) {
        if (index >= matrix.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Vector result = new Vector(matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            result.setElement(i, matrix[i].getElement(index));
        }

        return result;
    }

    public void setColumn(Vector array, int index) {
        if (index >= matrix.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (array == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < matrix.length; i++) {
            if (array.getSize() > i) {
                matrix[i].setElement(index, array.getElement(i));
            } else {
                matrix[i].setElement(index, 0);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder printMatrix = new StringBuilder("{");

        for (Vector temp : matrix) {
            printMatrix.append(temp.toString());
            printMatrix.append(',');
        }

        printMatrix.deleteCharAt(printMatrix.length() - 1);
        printMatrix.append('}');

        return printMatrix.toString();
    }

    public void transposition() {
        Matrix trans = new Matrix(matrix[0].getSize(), matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            trans.setLine(matrix[i], i);
        }

        matrix = trans.getMatrix();
    }

    public void multiplicationScalar(double scalar) {
        for (Vector temp : matrix) {
            temp.multiplyVectorScalar(scalar);
        }
    }

    public double getDeterminant() throws IllegalAccessError {//поиск определителя матрицы
        if (matrix.length != matrix[0].getSize() || matrix.length == 0) {
            throw new IllegalAccessError();
        }

        if (matrix.length == 1) {
            return matrix[0].getElement(0);
        }

        if (matrix.length == 2) {
            return matrix[0].getElement(0) * matrix[1].getElement(1) -
                    matrix[0].getElement(1) * matrix[1].getElement(0);
        }

        getReduction(matrix, 1);
        return determinant;
    }

    private void getReduction(Vector[] subMinor, double elemParentMinor) {
        if (subMinor.length > 1) {
            Vector[] tmpMinor = new Vector[subMinor.length - 1];

            for (int i = 0; i < tmpMinor.length; i++) {
                tmpMinor[i] = new Vector(subMinor[0].getSize() - 1);
            }

            for (int i = 0; i < subMinor[0].getSize(); i++) {
                for (int j = 1; j < subMinor.length; j++) {
                    for (int k = 0; k < subMinor[0].getSize(); k++) {
                        if (k < i) {
                            tmpMinor[j - 1].setElement(k, subMinor[j].getElement(k));
                        } else if (k > i) {
                            tmpMinor[j - 1].setElement(k - 1, subMinor[j].getElement(k));
                        }
                    }
                }

                double determinant = Math.pow(-1, i + 2) * subMinor[0].getElement(i) * elemParentMinor;
                getReduction(tmpMinor, determinant);
            }
        } else {
            determinant += elemParentMinor * subMinor[0].getElement(0);
        }
    }

    public Matrix multiplicationVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException();
        }

        if (vector.getSize() != matrix[0].getSize()) {
            throw new IllegalArgumentException();
        }

        Vector result = new Vector(vector.getSize());

        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;

            for (int j = 0; j < vector.getSize(); j++) {
                sum += matrix[i].getElement(j) * vector.getElement(j);
            }

            result.setElement(i, sum);
        }

        return new Matrix(new Vector[]{result});
    }

    public void add(Matrix array) throws Exception {
        if (array == null) {
            throw new NullPointerException();
        }

        if (matrix.length != array.matrix.length ||
                matrix[0].getSize() != array.matrix[0].getSize()) {
            throw new Exception();
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[i].add(array.matrix[i]);
        }
    }

    public void subtraction(Matrix array) throws Exception {
        if (array == null) {
            throw new NullPointerException();
        }

        if (matrix.length != array.matrix.length ||
                matrix[0].getSize() != array.matrix[0].getSize()) {
            throw new Exception();
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[i].subtraction(array.matrix[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) throws Exception {
        Matrix result = new Matrix(matrix1);

        for (int i = 0; i < matrix1.matrix.length; i++) {
            result.add(matrix2);
        }

        return result;
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) throws Exception {
        Matrix result = new Matrix(matrix1);

        for (int i = 0; i < matrix1.matrix.length; i++) {
            result.subtraction(matrix2);
        }

        return result;
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException();
        }

        int m = matrix1.matrix[0].getSize();
        int n = matrix2.matrix.length;

        if (m != n) {
            throw new Exception();
        }

        Matrix result = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;

                for (int k = 0; k < n; k++) {
                    sum += matrix1.matrix[i].getElement(k) * matrix2.matrix[k].getElement(j);
                }

                result.matrix[i].setElement(j, sum);
            }
        }

        return result;
    }
}
