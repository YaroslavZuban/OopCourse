package ru.academits.java.zuban.matrix;

import ru.academits.java.zuban.vector.Vector;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int numRows, int numColumns) {
        if (numRows <= 0) {
            throw new IllegalArgumentException("Количество строк меньше нуля. Переданный параметр равен: " + numRows);
        }

        if (numColumns <= 0) {
            throw new IllegalArgumentException("Количество столбцов меньше нуля. Переданный параметр равен: " + numColumns);
        }

        matrixRows = new Vector[numRows];

        for (int i = 0; i < numRows; i++) {
            matrixRows[i] = new Vector(numColumns);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        matrixRows = new Vector[matrix.matrixRows.length];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(matrix.matrixRows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].length != array[i + 1].length) {
                throw new IllegalArgumentException("Строки в матрицах не равны.");
            }
        }

        matrixRows = new Vector[array.length];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] array) {
        if (array == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getSize() != array[i + 1].getSize()) {
                throw new IllegalArgumentException("Строки в матрицах не равны.");
            }
        }

        matrixRows = new Vector[array.length];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(array[i]);
        }
    }

    public int getNumberColumns() {
        return matrixRows[0].getSize();
    }

    public int getNumberRow() {
        return matrixRows.length;
    }

    public Vector getRow(int index) {
        if (index >= matrixRows.length || index < 0) {
            throw new IllegalArgumentException("Выход за предел массива. Переданный параметр равен: " + index);
        }

        return new Vector(matrixRows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index >= matrixRows.length || index < 0) {
            throw new IllegalArgumentException("Выход за предел массива. Переданный параметр равен: " + index);
        }

        if (vector == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        if (vector.getSize() != matrixRows[index].getSize()) {
            throw new IllegalArgumentException("Передаваймый Vector не имеет нужной размерности. Размер переданного Vector равен: " + vector.getSize());
        }

        for (int i = 0; i < matrixRows[index].getSize(); i++) {
            matrixRows[index].setElement(i, vector.getElement(i));
        }
    }

    public Vector getColumn(int index) {
        if (index >= matrixRows[0].getSize() || index < 0) {
            throw new IllegalArgumentException("Выход за предел массива. Переданный параметр равен: " + index);
        }

        Vector result = new Vector(matrixRows.length);

        for (int i = 0; i < matrixRows.length; i++) {
            result.setElement(i, matrixRows[i].getElement(index));
        }

        return result;
    }

    public void setColumn(int index, Vector vector) {
        if (index >= matrixRows.length || index < 0) {
            throw new IllegalArgumentException("Выход за предел массива. Переданный параметр равен: " + index);
        }

        if (vector == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        if (vector.getSize() != matrixRows[index].getSize()) {
            throw new IllegalArgumentException("Передаваймый Vector не имеет нужной размерности. Размер переданного Vector равен: " + vector.getSize());
        }

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].setElement(index, vector.getElement(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector line : matrixRows) {
            stringBuilder.append(line);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void transpose() {
        Vector[] array = new Vector[matrixRows[0].getSize()];

        for (int i = 0; i < matrixRows[0].getSize(); i++) {
            array[i] = new Vector(getColumn(i));
        }

        matrixRows = array;
    }

    public void multiplyScalar(double scalar) {
        for (Vector temp : matrixRows) {
            temp.multiplyScalar(scalar);
        }
    }

    public double getDeterminant() throws UnsupportedOperationException {
        if (matrixRows.length != matrixRows[0].getSize() || matrixRows.length == 0) {
            throw new UnsupportedOperationException("Не является квадратной матрицей. Матрица размерностью " + matrixRows.length + " * " + matrixRows[0].getSize());
        }

        if (matrixRows.length == 2) {
            return matrixRows[0].getElement(0) * matrixRows[1].getElement(1) -
                    matrixRows[0].getElement(1) * matrixRows[1].getElement(0);
        }

        return getReduction(matrixRows, 1);
    }

    private double getReduction(Vector[] subMatrixes, double coefficient) {
        double determinant = 0.0;

        if (subMatrixes.length <= 1) {
            determinant += coefficient * subMatrixes[0].getElement(0);

        } else {
            Vector[] smallerMatrixes = new Vector[subMatrixes.length - 1];

            for (int i = 0; i < smallerMatrixes.length; i++) {
                smallerMatrixes[i] = new Vector(subMatrixes[0].getSize() - 1);
            }

            for (int i = 0; i < subMatrixes[0].getSize(); i++) {
                for (int j = 1; j < subMatrixes.length; j++) {
                    for (int k = 0; k < subMatrixes[0].getSize(); k++) {
                        if (k < i) {
                            smallerMatrixes[j - 1].setElement(k, subMatrixes[j].getElement(k));
                        } else if (k > i) {
                            smallerMatrixes[j - 1].setElement(k - 1, subMatrixes[j].getElement(k));
                        }
                    }
                }

                double minorDeterminant = Math.pow(-1, i) * subMatrixes[0].getElement(i) * coefficient;
                determinant += getReduction(smallerMatrixes, minorDeterminant);
            }
        }

        return determinant;
    }

    public Vector getMultiplyVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        if (vector.getSize() != matrixRows[0].getSize()) {
            throw new IllegalArgumentException("Не возможно произвести умножение матриц. " + vector.getSize() + " != " + matrixRows[0].getSize());
        }

        Vector result = new Vector(matrixRows.length);

        for (int i = 0; i < matrixRows.length; i++) {
            double sum = 0;

            for (int j = 0; j < vector.getSize(); j++) {
                sum += matrixRows[i].getElement(j) * vector.getElement(j);
            }

            result.setElement(i, sum);
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        checkEqualDimensionsMatrices(this, matrix);

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].add(matrix.matrixRows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Переданный объект не создан.");
        }

        checkEqualDimensionsMatrices(this, matrix);

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].subtract(matrix.matrixRows[i]);
        }
    }

    public static void checkEqualDimensionsMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixRows.length != matrix2.matrixRows.length ||
                matrix1.matrixRows[0].getSize() != matrix2.matrixRows[0].getSize()) {
            throw new IllegalArgumentException("Не возможно произвести сложение матриц. " +
                    "Размер первой матрицы: " + matrix1.matrixRows.length + " * " + matrix1.matrixRows[0].getSize() +
                    " Размер второй матрицы: " + matrix2.matrixRows.length + " * " + matrix2.matrixRows[0].getSize());
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkEqualDimensionsMatrices(matrix1, matrix2);

        matrix1.add(matrix2);

        return matrix1;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkEqualDimensionsMatrices(matrix1, matrix2);

        matrix1.subtract(matrix2);

        return matrix1;
    }

    public static Matrix getWork(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый передаваймый параметр равен null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй передаваймый параметр равен null");
        }

        int numberColumns = matrix1.matrixRows[0].getSize();
        int numberLine = matrix2.matrixRows.length;

        if (numberColumns != numberLine) {
            throw new IllegalArgumentException("Не возможно произвести сложение матриц. " +
                    "Размер первой матрицы: " + matrix1.matrixRows.length + " * " + matrix1.matrixRows[0].getSize() +
                    " Размер второй матрицы: " + matrix2.matrixRows.length + " * " + matrix2.matrixRows[0].getSize());
        }

        Matrix result = new Matrix(matrix1.matrixRows.length, matrix2.matrixRows[0].getSize());

        for (int i = 0; i < numberColumns; i++) {
            for (int j = 0; j < numberLine; j++) {
                double sum = 0;

                for (int k = 0; k < numberLine; k++) {
                    sum += matrix1.matrixRows[i].getElement(k) * matrix2.matrixRows[k].getElement(j);
                }

                result.matrixRows[i].setElement(j, sum);
            }
        }

        return result;
    }
}