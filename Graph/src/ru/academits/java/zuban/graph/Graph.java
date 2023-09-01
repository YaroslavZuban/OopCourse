package ru.academits.java.zuban.graph;

import java.util.*;

public class Graph {
    private final int numberNodes;
    private final int[][] adjacencyGraphMatrix;

    public Graph(int[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Двухмерный массив не должен быть null.");
        }

        if (matrix.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы равно 0.");
        }

        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Матрица не является квадратной");
        }

        this.numberNodes = matrix.length;

        adjacencyGraphMatrix = matrix;
    }

    public Object[] searchWidth() {
        List<Integer> result = new ArrayList<>(numberNodes);
        boolean[] visited = new boolean[numberNodes];

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node + 1);

            for (int i = 0; i < numberNodes; i++) {
                if (adjacencyGraphMatrix[node][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        return result.toArray();
    }

    public Object[] searchDepth() {
        List<Integer> result = new ArrayList<>(numberNodes);
        boolean[] visited = new boolean[numberNodes];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                result.add(node + 1);
                visited[node] = true;

                for (int i = 0; i < numberNodes; i++) {
                    if (adjacencyGraphMatrix[node][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

        return result.toArray();
    }
}