package ru.academits.java.zuban.matrix;

import ru.academits.java.zuban.vector.Vector;

public class Matrix {
    private Vector[] rows;

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

        rows = new Vector[matrix.getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Двухмерный массив не должен быть null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы равно 0.");
        }

        int maxSize = 0;

        for (double[] doubles : array) {
            maxSize = Math.max(maxSize, doubles.length);
        }

        if (maxSize == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы равно 0.");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < getColumnsCount(); i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] vectorArray) {
        if (vectorArray == null) {
            throw new NullPointerException("Массив из векторов не должен быть null.");
        }

        if (vectorArray.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        int maxSize = 0;

        for (Vector vector : vectorArray) {
            maxSize = Math.max(maxSize, vector.getSize());
        }

        rows = new Vector[vectorArray.length];

        for (int i = 0; i < getColumnsCount(); i++) {
            double[] vectorElements = new double[vectorArray[i].getSize()];

            for (int j = 0; j < vectorArray[i].getSize(); j++) {
                vectorElements[j] = vectorArray[i].getElement(j);
            }

            rows[i] = new Vector(maxSize, vectorElements);
        }
    }

    public int getRowCount() {
        return rows[0].getSize();
    }

    public int getColumnsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index <= -1 || index >= getRowCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы допустимого диапазона. Диапазон строк: [ 0;" + getRowCount() + " ], диапазон столбцов: [ 0;" + getColumnsCount() + " ]. Заданный индекс: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index <= -1 || index >= getRowCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы допустимого диапазона. Диапазон строк: [ 0;" + getRowCount() + " ], диапазон столбцов: [ 0;" + getColumnsCount() + " ]. Заданный индекс: " + index);
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != getRowCount()) {
            throw new IllegalArgumentException("Переданный вектор не имеет нужной размерности. Размер переданного Vector равен: " + vector.getSize() + " Диапазон строк: [ 0;" + getRowCount() + " ] ");
        }

        for (int i = 0; i < getRowCount(); i++) {
            rows[index].setElement(i, vector.getElement(i));
        }
    }

    public Vector getColumn(int index) {
        if (index <= -1 || index >= getRowCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы допустимого диапазона. Диапазон строк: [ 0;" + getRowCount() + " ], диапазон столбцов: [ 0;" + getColumnsCount() + " ]. Заданный индекс: " + index);
        }

        Vector result = new Vector(getColumnsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
            result.setElement(i, rows[i].getElement(index));
        }

        return result;
    }

    public void setColumn(int index, Vector vector) {
        if (index <= -1 || index >= getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы допустимого диапазона. Диапазон строк: [ 0;" + getRowCount() + " ], диапазон столбцов: [ 0;" + getColumnsCount() + " ]. Заданный индекс: " + index);
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Переданный вектор не имеет нужной размерности. Размер переданного вектора равен: " + vector.getSize() + "Диапазон строк: [ 0;" + getRowCount() + " ] ");
        }

        for (int i = 0; i < getColumnsCount(); i++) {
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
        Vector[] columns = new Vector[getRowCount()];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = getColumn(i);
        }

        rows = columns;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (getColumnsCount() != getRowCount()) {
            throw new IllegalArgumentException("Не является квадратной матрицей. Матрица размерностью " + getColumnsCount() + " * " + getRowCount());
        }

        if(getColumnsCount()==1){
            return rows[0].getElement(0);
        }

        if (getColumnsCount() == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) - rows[0].getElement(1) * rows[1].getElement(0);
        }

        return getDeterminant(rows, 1);
    }

    private static double getDeterminant(Vector[] matrix, double coefficient) {
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
                determinant += getDeterminant(subMatrix, minorDeterminant);
            }
        }

        return determinant;
    }

    public Vector getProduct(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != getRowCount()) {
            throw new IllegalArgumentException("Не возможно произвести умножение матриц. Размер переданного вектора: " + vector.getSize() + ". Количество столбцов матрицы: " + getRowCount());
        }

        Vector vectorResult = new Vector(getColumnsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
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

        checkDimensionalConsistency(this, matrix);

        for (int i = 0; i < getColumnsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null.");
        }

        checkDimensionalConsistency(this, matrix);

        for (int i = 0; i < getColumnsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static void checkDimensionalConsistency(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают. " + "Размер первой матрицы: " + matrix1.getColumnsCount() + " * " + matrix1.getRowCount() + " Размер второй матрицы: " + matrix2.getColumnsCount() + " * " + matrix2.getRowCount());
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        areMatricesNotNull(matrix1, matrix2);
        checkDimensionalConsistency(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        areMatricesNotNull(matrix1, matrix2);
        checkDimensionalConsistency(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        areMatricesNotNull(matrix1, matrix2);

        if (matrix1.getRowCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Не возможно произвести умножение матриц. " + "Размер первой матрицы: " + matrix1.getColumnsCount() + " * " + matrix1.getRowCount() + ". Размер второй матрицы: " + matrix2.getColumnsCount() + " * " + matrix2.getRowCount());
        }

        Matrix result = new Matrix(matrix1.getColumnsCount(), matrix2.getRowCount());

        for (int i = 0; i < matrix1.getColumnsCount(); i++) {
            for (int j = 0; j < matrix2.getRowCount(); j++) {
                double sum = 0;

                for (int k = 0; k < matrix2.getColumnsCount(); k++) {
                    sum += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                result.rows[i].setElement(j, sum);
            }
        }

        return result;
    }

    private static void areMatricesNotNull(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый параметр равен null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй параметр равен null");
        }
    }
}