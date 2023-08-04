package ru.academits.java.zuban.matrix;

import ru.academits.java.zuban.vector.Vector;

public class Matrix {
    private Vector[] rows;
    private int rowsCount;
    private int columnsCount;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк меньше нуля. Переданный параметр равен: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов меньше нуля. Переданный параметр равен: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null.");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Двухмерный массив не должен быть null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        int maxSize = 0;

        for (int i = 0; i < array.length - 1; i++) {
            maxSize = Math.max(array[i].length, array[i + 1].length);
        }

        if (maxSize == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        columnsCount = array.length;
        rowsCount = maxSize;

        rows = new Vector[columnsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] array) {
        if (array == null) {
            throw new NullPointerException("Массив из векторов не должен быть null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        int maxSize = 0;

        for (int i = 0; i < array.length - 1; i++) {
            maxSize = Math.max(array[i].getSize(), maxSize);
        }

        columnsCount = array.length;
        rowsCount = maxSize;

        rows = new Vector[columnsCount];

        for (int i = 0; i < rows.length; i++) {
            double[] elementsVector = new double[array[i].getSize()];

            for (int j = 0; j < array[i].getSize(); j++) {
                elementsVector[j] = array[i].getElement(j);
            }

            rows[i] = new Vector(maxSize, elementsVector);
        }
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Выход за предел массива. Интервал равен [ " + 0 + " ; " + rows.length + " ]");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Выход за предел массива. Интервал равен [ " + 0 + " ; " + rows.length + " ]");
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != rowsCount) {
            throw new IllegalArgumentException("Передаваймый вектор не имеет нужной размерности. Размер переданного Vector равен: " + vector.getSize());
        }

        for (int i = 0; i < rowsCount; i++) {
            rows[index].setElement(i, vector.getElement(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IllegalArgumentException("Выход за предел массива. Интервал равен [ " + 0 + " ; " + rows.length + " ]");
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            result.setElement(i, rows[i].getElement(index));
        }

        return result;
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Выход за предел массива. Интервал равен [ " + 0 + " ; " + rows.length + " ]");
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != rows[index].getSize()) {
            throw new IllegalArgumentException("Передаваймый вектор не имеет нужной размерности. Размер переданного вектора равен: " + vector.getSize());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].setElement(index, vector.getElement(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void transpose() {
        Vector[] newMatrixTranspose = new Vector[rowsCount];

        for (int i = 0; i < rows[0].getSize(); i++) {
            newMatrixTranspose[i] = getColumn(i);
        }

        rows = newMatrixTranspose;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Не является квадратной матрицей. Матрица размерностью " + rows.length + " * " + rows[0].getSize());
        }

        if (rows.length == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) -
                    rows[0].getElement(1) * rows[1].getElement(0);
        }

        return getReduction(rows, 1);
    }

    private static double getReduction(Vector[] matrix, double coefficient) {
        double determinant = 0.0;

        if (matrix.length <= 1) {
            return coefficient * matrix[0].getElement(0);
        } else {
            Vector[] subMatrix = new Vector[matrix.length - 1];

            for (int i = 0; i < subMatrix.length; i++) {
                subMatrix[i] = new Vector(matrix[0].getSize() - 1);
            }

            for (int i = 0; i < matrix[0].getSize(); i++) {
                for (int j = 1; j < matrix.length; j++) {
                    for (int k = 0; k < matrix[0].getSize(); k++) {
                        if (k < i) {
                            subMatrix[j - 1].setElement(k, matrix[j].getElement(k));
                        } else if (k > i) {
                            subMatrix[j - 1].setElement(k - 1, matrix[j].getElement(k));
                        }
                    }
                }

                double minorDeterminant = Math.pow(-1, i) * matrix[0].getElement(i) * coefficient;
                determinant += getReduction(subMatrix, minorDeterminant);
            }
        }

        return determinant;
    }

    public Vector getProduct(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Не возможно произвести умножение матриц. Размер переданного вектора: " + vector.getSize() +
                    ". Количество столбцов матрицы: " + rows[0].getSize());
        }

        Vector vectorResult = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double sum = 0;

            for (int j = 0; j < vector.getSize(); j++) {
                sum += rows[i].getElement(j) * vector.getElement(j);
            }

            vectorResult.setElement(i, sum);
        }

        return vectorResult;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null.");
        }

        checkDimensionalEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null.");
        }

        checkDimensionalEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static void checkDimensionalEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length ||
                matrix1.rows[0].getSize() != matrix2.rows[0].getSize()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают. " +
                    "Размер первой матрицы: " + matrix1.rows.length + " * " + matrix1.rows[0].getSize() +
                    " Размер второй матрицы: " + matrix2.rows.length + " * " + matrix2.rows[0].getSize());
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkingMatrixNull(matrix1, matrix2);
        checkDimensionalEquality(matrix1, matrix2);

        Matrix matrix3 = new Matrix(matrix1);
        matrix3.add(matrix2);

        return matrix3;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkingMatrixNull(matrix1, matrix2);
        checkDimensionalEquality(matrix1, matrix2);

        Matrix matrix3 = new Matrix(matrix1);
        matrix3.subtract(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        checkingMatrixNull(matrix1, matrix2);

        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException("Не возможно произвести умножение матриц. " +
                    "Размер первой матрицы: " + matrix1.rows.length + " * " + matrix1.rows[0].getSize() +
                    ". Размер второй матрицы: " + matrix2.rows.length + " * " + matrix2.rows[0].getSize());
        }

        Matrix result = new Matrix(matrix1.rows.length, matrix2.rows[0].getSize());

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.rows[0].getSize(); j++) {
                double sum = 0;

                for (int k = 0; k < matrix2.rows.length; k++) {
                    sum += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                result.rows[i].setElement(j, sum);
            }
        }

        return result;
    }

    private static void checkingMatrixNull(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый параметр равен null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй параметр равен null");
        }
    }
}