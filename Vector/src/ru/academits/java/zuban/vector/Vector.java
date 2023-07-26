package ru.academits.java.zuban.vector;

import java.util.Arrays;

public class Vector {
    private double[] elementData;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность Vector может быть только положительна.");
        }

        elementData = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Объект Vector должен быть создан.");
        }

        elementData = Arrays.copyOf(vector.elementData, vector.elementData.length);
    }

    public Vector(double[] elementData) {
        if (elementData == null) {
            throw new NullPointerException("Объект массива должен быть создан.");
        }

        if (elementData.length == 0) {
            throw new IllegalArgumentException("Не возможно создать Vector размерностью 0.");
        }

        this.elementData = Arrays.copyOf(elementData, elementData.length);
    }

    public Vector(int size, double[] elementData) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность Vector может быть только положительна.");
        }

        if (elementData == null) {
            throw new NullPointerException("Объект массива должен быть создан.");
        }

        if (elementData.length == 0) {
            throw new IllegalArgumentException("Не возможно создать Vector размерностью 0.");
        }

        int maxSize = Math.max(size, elementData.length);

        this.elementData = Arrays.copyOf(elementData, maxSize);
    }

    public int getSize() {
        return elementData.length;
    }

    public double getElement(int index) {
        return elementData[index];
    }

    public void setElement(int index, double element) {
        elementData[index] = element;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Объект Vector должен быть создан.");
        }

        elementData = copyArrayIfNeeded(elementData, vector.elementData.length);

        for (int i = 0; i < elementData.length; i++) {
            if (i >= vector.elementData.length) {
                break;
            }

            elementData[i] += vector.elementData[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Объект Vector должен быть создан.");
        }

        elementData = copyArrayIfNeeded(elementData, vector.elementData.length);

        for (int i = 0; i < elementData.length; i++) {
            if (i >= vector.elementData.length) {
                break;
            }

            elementData[i] -= vector.elementData[i];
        }
    }

    public double[] copyArrayIfNeeded(double[] array, int newSize) {
        if (array.length > newSize) {
            return Arrays.copyOf(array, newSize);
        }

        return array;
    }

    public void multiplyScalar(double scalar) {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double v : this.elementData) {
            sum += v * v;
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
        return Arrays.equals(elementData, vector.elementData);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elementData);
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("{");

        for (double number : elementData) {
            toString.append(number).append(", ");
        }

        toString.delete(toString.length() - 2, toString.length());
        toString.append("}");

        return toString.toString();
    }

    public static Vector getAdd(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new NullPointerException("Объект Vector должен быть создан.");
        }

        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getSubtract(Vector minuend, Vector subtrahend) {
        if (minuend == null || subtrahend == null) {
            throw new NullPointerException("Объект Vector должен быть создан.");
        }

        Vector result = new Vector(minuend);
        result.subtract(subtrahend);

        return result;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new NullPointerException("Объект Vector должен быть создан.");
        }

        int maxSize = Math.max(vector1.elementData.length, vector2.elementData.length);

        double result = 0;

        for (int i = 0; i < maxSize; i++) {
            double elementVector1 = 0;
            double elementVector2 = 0;

            if (i < vector1.elementData.length) {
                elementVector1 = vector1.elementData[i];
            }

            if (i < vector2.elementData.length) {
                elementVector2 = vector2.elementData[i];
            }

            result += elementVector1 * elementVector2;
        }

        return result;
    }
}
