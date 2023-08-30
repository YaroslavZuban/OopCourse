package ru.academits.java.zuban.matrix;

import ru.academits.java.zuban.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Значение параметра должно быть больше или равно 0." +
                    " Переданное значение: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Значение параметра должно быть больше или равно 0." +
                    " Переданное значение: " + columnsCount);
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

        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < getRowsCount(); i++) {
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

        int maxColumnCounts = 0;

        for (double[] row : array) {
            maxColumnCounts = Math.max(maxColumnCounts, row.length);
        }

        if (maxColumnCounts == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы равно 0.");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i] = new Vector(maxColumnCounts, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Массив из векторов не должен быть null.");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть размером 0.");
        }

        rows = new Vector[vectors.length];

        int maxColumnCounts = 0;

        for (Vector vector : vectors) {
            maxColumnCounts = Math.max(maxColumnCounts, vector.getSize());
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i] = new Vector(maxColumnCounts);
            rows[i].add(vectors[i]);
        }
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс выходит за допустимый диапазон строк: [0, "
                    + (getRowsCount() - 1) + "]. Заданный индекс: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс выходит за допустимый диапазон строк: [0, "
                    + (getRowsCount() - 1) + "]. Заданный индекс: " + index);
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Переданный вектор имеет неверную размерность. " +
                    "Размер переданного вектора: " + vector.getSize() +
                    ". Допустимая размерность: " + getColumnsCount() + ".");
        }

        for (int i = 0; i < getColumnsCount(); i++) {
            rows[index].setElement(i, vector.getElement(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс выходит за допустимый диапазон столбцов: [0, "
                    + (getColumnsCount() - 1) + "]. Заданный индекс: " + index);
        }

        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setElement(i, rows[i].getElement(index));
        }

        return result;
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс выходит за допустимый диапазон столбцов:" +
                    " [0, " + (getColumnsCount() - 1) + "]. Заданный индекс: " + index);
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != getRowsCount()) {
            throw new IllegalArgumentException("Переданный вектор имеет неверную размерность." +
                    " Размер переданного вектора: " + vector.getSize() +
                    ". Допустимая размерность: " + getRowsCount() + ".");
        }

        for (int i = 0; i < getRowsCount(); i++) {
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
        Vector[] columns = new Vector[getColumnsCount()];

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
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Не является квадратной матрицей." +
                    " Матрица размерностью " + getRowsCount() + " * " + getColumnsCount());
        }

        if (getRowsCount() == 1) {
            return rows[0].getElement(0);
        }

        if (getRowsCount() == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) -
                    rows[0].getElement(1) * rows[1].getElement(0);
        }

        return getDeterminant(rows, 1);
    }

    private static double getDeterminant(Vector[] matrix, double coefficient) {
        if (matrix.length <= 1) {
            return coefficient * matrix[0].getElement(0);
        }

        if (matrix.length == 2) {
            return coefficient * (matrix[0].getElement(0) * matrix[1].getElement(1) -
                    matrix[0].getElement(1) * matrix[1].getElement(0));
        }

        Vector[] subMatrix = new Vector[matrix.length - 1];

        for (int i = 0; i < subMatrix.length; i++) {
            subMatrix[i] = new Vector(matrix[0].getSize() - 1);
        }

        double determinant = 0.0;

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

        return determinant;
    }

    public Vector getProduct(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Несовместимые размеры вектора и матрицы для данной операции." +
                    " Размер переданного вектора: " + vector.getSize() +
                    ". Количество столбцов матрицы: " + getColumnsCount());
        }

        Vector resultVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            resultVector.setElement(i, Vector.getDotProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null.");
        }

        validateSizeEquality(this, matrix);

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null.");
        }

        validateSizeEquality(this, matrix);

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static void validateSizeEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают." +
                    " Размер первой матрицы: " + matrix1.getRowsCount() + " * " + matrix1.getColumnsCount() +
                    " Размер второй матрицы: " + matrix2.getRowsCount() + " * " + matrix2.getColumnsCount());
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        validateMatricesNotNull(matrix1, matrix2);
        validateSizeEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        validateMatricesNotNull(matrix1, matrix2);
        validateSizeEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        validateMatricesNotNull(matrix1, matrix2);

        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Невозможно произвести умножение матриц. " +
                    "Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы. " +
                    "Размер первой матрицы: " + matrix1.getRowsCount() + " * " + matrix1.getColumnsCount() +
                    ". Размер второй матрицы: " + matrix2.getRowsCount() + " * " + matrix2.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double sum = 0;

                for (int k = 0; k < matrix2.getRowsCount(); k++) {
                    sum += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                result.rows[i].setElement(j, sum);
            }
        }

        return result;
    }

    private static void validateMatricesNotNull(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый параметр равен null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй параметр равен null");
        }
    }
}