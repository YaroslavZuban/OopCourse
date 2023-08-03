package ru.academits.java.zuban.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора может быть только положительна. Переданный параметр равен: " + size);
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] elements) {
        if (elements == null) {
            throw new NullPointerException("Массив не должен быть null.");
        }

        if (elements.length == 0) {
            throw new IllegalArgumentException("Не возможно создать вектор размерностью 0.");
        }

        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public Vector(int size, double[] elements) {
        if (size < 0) {
            throw new IllegalArgumentException("Размерность вектора может быть только положительна. Переданный параметр равен: " + size);
        }

        if (elements == null) {
            throw new NullPointerException("Массив не должен быть null.");
        }

        if (elements.length == 0) {
            throw new IllegalArgumentException("Не возможно создать вектор размерностью 0.");
        }

        this.elements = Arrays.copyOf(elements, size);
    }

    public int getSize() {
        return elements.length;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public void setElement(int index, double element) {
        elements[index] = element;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        elements = copyArrayIfNeeded(elements, vector.elements.length);

        for (int i = 0; i < elements.length && i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        elements = copyArrayIfNeeded(elements, vector.elements.length);

        for (int i = 0; i < elements.length && i < vector.elements.length; i++){
            elements[i] -= vector.elements[i];
        }
    }

    private static double[] copyArrayIfNeeded(double[] array, int size) {
        if (array.length < size) {
            return Arrays.copyOf(array, size);
        }

        return array;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double number : elements) {
            sum += number * number;
        }

        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double number : elements) {
            stringBuilder.append(number).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null ) {
            throw new NullPointerException("Первый вектор не должен быть null.");
        }

        if(vector2==null){
            throw new NullPointerException("Второй вектор не должен быть null.");
        }

        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getDifferentiate(Vector minuend, Vector subtrahend) {
        if (minuend == null || subtrahend == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        Vector result = new Vector(minuend);
        result.subtract(subtrahend);

        return result;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        int minSize = Math.min(vector1.elements.length, vector2.elements.length);

        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.elements[i] * vector2.elements[i];
        }

        return result;
    }
}