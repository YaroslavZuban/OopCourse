package ru.academits.java.zuban.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    /**
     * размерность n, все компоненты равны 0
     */
    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер массива введен не верно.");
        }

        this.array = new double[n];
    }

    /**
     * конструктор копирования
     */
    public Vector(Vector vector) {
        this.array = new double[vector.getSize()];

        System.arraycopy(vector.array, 0, this.array, 0, this.array.length);
    }

    /**
     * заполнение вектора значениями из массива
     */
    public Vector(double[] array) {
        this.array = new double[array.length];

        System.arraycopy(array, 0, this.array, 0, this.array.length);
    }

    /**
     * заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
     */
    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер массива введен не верно.");
        }

        if (array.length < n) {
            this.array = new double[n];
        } else {
            this.array = new double[array.length];
        }

        System.arraycopy(array, 0, this.array, 0, array.length);
    }

    /**
     * Метод getSize() для получения размерности вектора
     */
    public int getSize() {
        return array.length;
    }

    public double[] getArray() {
        return array;
    }


    /**
     * Реализовать метод toString(), чтобы выдавал информацию о векторе в  формате { значения компонент через запятую }
     */
    @Override
    public String toString() {
        StringBuffer infoArray = new StringBuffer("{");


        for (double number :
                this.array) {
            infoArray.append(number + ", ");
        }

        infoArray.delete(infoArray.length() - 2, infoArray.length());
        infoArray.append("}");

        return infoArray.toString();
    }

    public void add(Vector vector) {
        copyArrayIfNeeded(vector.array, this.array);

        Arrays.setAll(this.array, i -> this.array[i] + vector.array[i]);
    }

    public void subtraction(Vector vector) {
        copyArrayIfNeeded(vector.array, this.array);

        Arrays.setAll(this.array, i -> this.array[i] - vector.array[i]);
    }

    private void copyArrayIfNeeded(double[] array1, double[] array2) {
        if (array1.length > array2.length) {
            copyArray(array2, array1.length);
        } else if (array1.length < array2.length) {
            copyArray(array1, array2.length);
        }
    }

    private void copyArray(double[] dest, int size) {
        double[] arrayTemp = dest;
        dest = new double[size];

        System.arraycopy(arrayTemp, 0, dest, 0, arrayTemp.length);
    }

    public void multiplyVectorScalar(int scalar) {
        Arrays.setAll(this.array, i -> this.array[i] * scalar);
    }

    public void revers() {
        multiplyVectorScalar(-1);
    }

    public double length() {
        double sum = 0;

        for (double v : this.array) {
            sum += Math.pow(v, 2);
        }

        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (this.array.length == ((Vector) o).array.length) return true;

        Vector vector = (Vector) o;
        return Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public double getElement(int index) {
        return this.array[index];
    }

    public void setElement(int index, double element) {
        this.array[index] = element;
    }

    public static Vector subtraction(Vector minuend, Vector subtrahend) {
        Vector result = new Vector(minuend);
        result.subtraction(subtrahend);

        return result;
    }

    public static double scalar(Vector vector1, Vector vector2) {
        if (vector1.getSize() > vector2.getSize()) {
            vector2 = duplicateVectorWithSize(vector2, vector1.getSize());
        } else if (vector1.getSize() < vector2.getSize()) {
            vector1 = duplicateVectorWithSize(vector1, vector2.getSize());
        }

        double result = 0;

        for (int i = 0; i < vector1.getSize(); i++) {
            result += vector1.getElement(i) * vector2.getElement(i);
        }

        return result;
    }

    private static Vector duplicateVectorWithSize(Vector vector, int newSize) {
        double[] newArray = new double[newSize];
        System.arraycopy(vector.getArray(), 0, newArray, 0, Math.min(vector.getSize(), newSize));
        return new Vector(newArray);
    }
}
